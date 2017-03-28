package org.tinkerbell.security.service;

import org.tinkerbell.security.entity.po.AuthRole;
import org.tinkerbell.security.entity.po.AuthUser;
import org.tinkerbell.security.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户-角色的认证授权
 * Created by nn_liu on 2017/3/9.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    /**
     * 获取用户角色信息，绑定为UserDetails实例
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        AuthUser user = authUserRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (AuthRole role : user.getAuthRoles()) {

            if (role.getRoleName().equalsIgnoreCase("admin")){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ACTUATOR"));
            }

            // 注意：这里要ROLE_加上前缀，否则在创建角色而的时候统一加上
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        }

        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
