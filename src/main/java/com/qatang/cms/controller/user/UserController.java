package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public String list(ModelMap model) {
        List<User> userList = userService.getList();
        model.addAttribute(userList);
        return "user/userList";
    }

    @RequestMapping(value = "/input")
    public String input() {
        return "user/userInput";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create() {
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update() {
        return "success";
    }

    @RequestMapping(value = "/del")
    public String delete(Long id) {
        if (id == null) {
            logger.error("删除用户，id为空");
            return "/login";
        }
        userService.delete(id);
        return "success";
    }
}
