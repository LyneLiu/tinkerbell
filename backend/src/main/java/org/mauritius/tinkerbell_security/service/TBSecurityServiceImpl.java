package org.mauritius.tinkerbell_security.service;

import org.mauritius.tinkerbell_security.entity.bo.RoleBean;
import org.mauritius.tinkerbell_security.entity.bo.UserBean;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.mapper.UserMapper;
import org.mauritius.tinkerbell_security.service.spi.SecurityService;
import org.mauritius.tinkerbell_security.service.spi.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供spring security权限的相关api
 * Created by nn_liu on 2017/3/13.
 */

@Service
public class TBSecurityServiceImpl extends BaseService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    /**
     * 根据userName查询
     *
     * @param userName
     * @return
     */
    public UserBean findUserByName(String userName) {

        UserBean userBean = null;

        try {
            AuthUser authUser = userService.findByUsername(userName);
            userBean = UserMapper.MAPPER.toUser(authUser);
        } catch (Exception e) {
            logger.error("user service error:", e);
        }

        return userBean;
    }

    /**
     * 注册用户信息
     *
     * @param userBean
     */
    public boolean register(UserBean userBean) {

        try {

            AuthUser authUser = UserMapper.MAPPER.fromUser(userBean);

            //userService.save(authUser);
            securityService.autologin(userBean.getUserName(), userBean.getPassword());
        } catch (Exception e) {
            logger.error("register error:", e);
            return false;
        }

        return true;
    }

    public boolean authorize(UserBean userBean) {
        //TODO:用户登录验证
        return false;
    }
}
