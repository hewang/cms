package com.qatang.cms.validator.impl.user;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by qatang on 14-6-12.
 */
@Component
public class SigninValidator extends AbstractValidator<UserForm> {
    @Autowired
    private UserService userService;
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        logger.info("开始验证userForm参数");
        if (userForm == null) {
            String msg = String.format("userForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        if (StringUtils.isEmpty(userForm.getUsername())) {
            String msg = String.format("用户名不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getUsername().length() < 6 || userForm.getUsername().length() > 32) {
            String msg = String.format("用户名长度必须在6-32个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (!this.checkUsername(userForm.getUsername())) {
            String msg = String.format("用户名格式错误");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
//        User user = userService.getByUsername(userForm.getUsername());
//        if (user == null) {
//            String msg = String.format("用户不存在");
//            logger.error(msg);
//            throw new ValidateFailedException(msg);
//        }
//        if (user.getValid() == EnableDisableStatus.DISABLE) {
//            String msg = String.format("该用户无效");
//            logger.error(msg);
//            throw new ValidateFailedException(msg);
//        }

        if (StringUtils.isEmpty(userForm.getPassword())) {
            String msg = String.format("密码不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getPassword().length() < 6 || userForm.getPassword().length() > 16) {
            String msg = String.format("密码长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        if (StringUtils.isEmpty(userForm.getCaptchaExpected())) {
            String msg = String.format("内部验证码不能为空，请联系管理员处理！");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        logger.info("expected captcha : {}", userForm.getCaptchaExpected());
        logger.info("input captcha : {}", userForm.getCaptcha());
        if (!userForm.getCaptcha().equals(userForm.getCaptchaExpected())) {
            String msg = String.format("验证码错误");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
