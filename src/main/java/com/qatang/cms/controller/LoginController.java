package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
@SessionAttributes(CommonConstants.KAPTCHA_SESSION_KEY)
public class LoginController extends BaseController {
    @Autowired
    private IValidator<UserForm> loginValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(String errorMessage) {
        return "forward:/index.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserForm userForm, @ModelAttribute(CommonConstants.KAPTCHA_SESSION_KEY) String captchaExpected, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        userForm.setCaptchaExpected(captchaExpected);
        try {
            loginValidator.validate(userForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/login";
        }

        User user = userService.getByUsername(userForm.getUsername());
        if (user == null || !user.getPassword().equals(DigestUtils.md5Hex(userForm.getPassword()))) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "用户名或密码错误！");
            return "redirect:/login";
        }
        request.getSession().setAttribute(CommonConstants.USER_SESSION_KEY, user);
        return "redirect:/login/success";
    }
}
