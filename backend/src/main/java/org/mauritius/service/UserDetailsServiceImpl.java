package org.mauritius.service;

import org.mauritius.domain.tinkerbell.AuthRole;
import org.mauritius.domain.tinkerbell.AuthUser;
import org.mauritius.repository.tinkerbell.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nn_liu on 2017/3/9.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    /**
     * 获取用户角色信息，绑定为UserDetails实例
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (AuthRole role:user.getAuthRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new User(user.getUserName(),user.getPassword(),grantedAuthorities);
    }
}
