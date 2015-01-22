package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleResource;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.service.role.RoleResourceService;
import com.qatang.cms.service.user.UserRoleService;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.IValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
@SessionAttributes(CommonConstants.KAPTCHA_SESSION_KEY)
public class SigninController extends BaseController {
    @Autowired
    private IValidator<UserForm> signinValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    CacheManager cacheManager;


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinPage() {
        return "user/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(UserForm userForm, @ModelAttribute(CommonConstants.KAPTCHA_SESSION_KEY) String captchaExpected, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            signinValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/signin";
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userForm.getUsername(), userForm.getPassword());
        if (userForm.getRememberMe() == null) {
            usernamePasswordToken.setRememberMe(false);
        } else {
            usernamePasswordToken.setRememberMe(true);
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            if (e instanceof IncorrectCredentialsException) {
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "用户名或密码错误！");
            } else {
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            }
            logger.error(e.getMessage(), e);
            return "redirect:/signin";
        }

        User user = (User)subject.getPrincipal();
        user.setLastLoginTime(user.getLoginTime());
        user.setLoginTime(new Date());
        userService.update(user);
        List<Resource> tempList = resourceService.query(user.getId(), ResourcesType.MENU);
        List<Resource> resourceList = new ArrayList<>();
        Map<Long, Resource> resourceListMap = new LinkedHashMap<>();
        if (tempList != null) {
            for (Resource resource : tempList) {
                if (resource.getParentID() == 0) {
                    resource.setChildren(new ArrayList<Resource>());
                    resourceListMap.put(resource.getId(), resource);
                }
            }
            for (Resource resource : tempList) {
                if (resource.getParentID() != 0) {
                    if (resourceListMap.get(resource.getParentID()) != null) {
                        resourceListMap.get(resource.getParentID()).getChildren().add(resource);
                    }
                }
            }
        }
        for (Long key : resourceListMap.keySet()) {
            resourceList.add(resourceListMap.get(key));
        }
        Cache<Long, List<Resource>> rememberMeSessionCache = cacheManager.getCache("rememberMeSessionCache");
        rememberMeSessionCache.put(user.getId(), resourceList);
        return "redirect:/dashboard";
    }
}
