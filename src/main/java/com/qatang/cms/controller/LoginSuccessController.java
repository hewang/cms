package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
@SessionAttributes(CommonConstants.USER_SESSION_KEY)
public class LoginSuccessController extends BaseController {
    @RequestMapping("/login/success")
    public String loginSuccess(@ModelAttribute(CommonConstants.USER_SESSION_KEY) User user, ModelMap modelMap) {
        if (user == null) {
//            userForm.setErrorMessage("用户session超时，请重新登录！");
            return "forward:/index.jsp";
        }
        modelMap.addAttribute(user);
        return "/user/loginSuccess";
    }
}
