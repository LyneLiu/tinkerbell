package org.tinkerbell.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.tinkerbell.security.entity.bo.UserBean;
import org.tinkerbell.security.entity.po.AuthUser;
import org.tinkerbell.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinkerbell.security.service.spi.SecurityService;
import org.tinkerbell.security.service.spi.UserService;

/**
 * 提供spring security权限的相关api
 * Created by nn_liu on 2017/3/13.
 */

@Service
public class TBSecurityServiceImpl extends BaseService implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 查询用户信息
     * @return
     */
    @Override
    public String findLoggedInUsername() {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    /**
     * 登陆注册用户信息
     * @param username
     * @param password
     */
    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
        token.setDetails(userDetails);

        authenticationManager.authenticate(token);

        if (token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("Auto login %s successfully!", username));
        }

    }

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
            autologin(userBean.getUserName(), userBean.getPassword());
            
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
