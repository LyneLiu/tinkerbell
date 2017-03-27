package org.tinkerbell.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tinkerbell.security.entity.bo.RoleBean;
import org.tinkerbell.security.entity.bo.UserBean;
import org.tinkerbell.security.entity.po.AuthUser;
import org.tinkerbell.security.mapper.UserMapper;
import org.tinkerbell.security.service.TBSecurityServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验用户注册 or 登录信息
 * Created by nn_liu on 2017/3/9.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private TBSecurityServiceImpl tBSecurityServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserBean userBean = (UserBean)target;

        if (userBean.getRoles() == null) {
            RoleBean roleBean = new RoleBean();
            roleBean.setRoleName("guest");
            List<RoleBean> roleBeans = new ArrayList<RoleBean>();
            roleBeans.add(roleBean);
            userBean.setRoles(roleBeans);
        }

        AuthUser user = UserMapper.MAPPER.fromUser(userBean);
        user.setPasswordConfirm(user.getPassword() == null? "guest":user.getPassword());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
        if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.username");
        }
        if (tBSecurityServiceImpl.findUserByName(user.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
