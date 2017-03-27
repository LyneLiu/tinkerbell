package org.tinkerbell.service;

import org.tinkerbell.common.RegisterException;
import org.tinkerbell.common.UserValidator;
import org.tinkerbell.common.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.tinkerbell.entity.vo.UserInfo;
import org.tinkerbell.security.entity.bo.UserBean;
import org.tinkerbell.security.service.TBSecurityServiceImpl;

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

            UserBean userBean = buildUserBean(userInfo);

            userValidator.validate(userBean, bindingResult);

            /* 临时关闭校验
            if (bindingResult.hasErrors()) {
                logger.error(bindingResult.getFieldError().toString());
                return ResultEnum.FAILURE.getValue();
            }*/

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
        } catch (Exception e) {
            logger.error("register error:", e);
            return ResultEnum.FAILURE.getValue();
        }
    }

    private UserBean buildUserBean(UserInfo userInfo) {
        UserBean userBean = new UserBean();
        userBean.setUserName(userInfo.getUserName());
        userBean.setPassword(userInfo.getPassword());
        return userBean;
    }

    public boolean auth(UserInfo userInfo) {

        UserBean userBean = buildUserBean(userInfo);
        return tBSecurityServiceImpl.authorize(userBean);

    }

}
