package org.mauritius.tinkerbell_portal.service;

import org.mauritius.tinkerbell_portal.common.RegisterException;
import org.mauritius.tinkerbell_portal.common.ResultEnum;
import org.mauritius.tinkerbell_portal.common.UserValidator;
import org.mauritius.tinkerbell_portal.entity.vo.UserInfo;
import org.mauritius.tinkerbell_security.entity.bo.RoleBean;
import org.mauritius.tinkerbell_security.entity.bo.UserBean;
import org.mauritius.tinkerbell_security.service.TBSecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/15.
 */
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private TBSecurityServiceImpl tBSecurityServiceImpl;

    @Autowired
    private UserValidator userValidator;

    public String register(UserInfo userInfo) {
        try {
            BindingResult bindingResult = new BeanPropertyBindingResult(userInfo, "binding_object");
            userValidator.validate(userInfo, bindingResult);

            if (bindingResult.hasErrors()) {
                logger.error(bindingResult.getFieldError().toString());
                return ResultEnum.FAILURE.getValue();
            }

            UserBean userBean = buildUserBean(userInfo);
            boolean result = false;
            if (userBean != null) {
                result = tBSecurityServiceImpl.register(userBean);
            }

            if (result) {
                return ResultEnum.SUCCESS.getValue();
            } else {
                return ResultEnum.FAILURE.getValue();
            }

        } catch (RegisterException e) {
            logger.error("register error:", e);
            return ResultEnum.FAILURE.getValue();
        }
    }

    private UserBean buildUserBean(UserInfo userInfo) {
        UserBean userBean = new UserBean();
        userBean.setUserName(userInfo.getUserName());
        userBean.setPassword(userInfo.getPassword());
        userBean.setRoles(buildRoles(userInfo.getRoles(), userInfo.getPerms()));
        return userBean;
    }

    public boolean auth(UserInfo userInfo) {

        UserBean userBean = buildUserBean(userInfo);
        return tBSecurityServiceImpl.authorize(userBean);

    }

    private List<RoleBean> buildRoles(List<String> roles, List<String> perms) {
        //TODO:构建用户角色
        return null;
    }
}
