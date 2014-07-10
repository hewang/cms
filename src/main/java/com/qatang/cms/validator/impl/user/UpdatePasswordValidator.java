package com.qatang.cms.validator.impl.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by JSH on 2014/7/6.
 */
@Component
public class UpdatePasswordValidator extends AbstractValidator<UserForm> {
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
        if (StringUtils.isEmpty(userForm.getPassword())) {
            String msg = String.format("旧密码不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getPassword().length() < 6 || userForm.getPassword().length() > 16) {
            String msg = String.format("密码长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getId() == null) {
            String msg = String.format("更改密码，用户id为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        User user = userService.get(userForm.getId());
        if (user == null) {
            String msg = String.format("所要更改密码的用户不存在");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (!DigestUtils.md5Hex(userForm.getPassword()).equals(user.getPassword())) {
            String msg = String.format("旧密码输入错误，请重新输入");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getNewPassword())) {
            String msg = String.format("新密码为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getNewPassword().length() < 6 || userForm.getNewPassword().length() > 16) {
            String msg = String.format("密码长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getConPassword())) {
            String msg = String.format("确认密码不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (!userForm.getNewPassword().equals(userForm.getConPassword())) {
            String msg = String.format("新密码与确认密码不一致");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}