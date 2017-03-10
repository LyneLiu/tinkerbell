package org.mauritius.tinkerbell_security.service;

import org.mauritius.tinkerbell_security.entity.po.AuthRole;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.repository.AuthRoleRepository;
import org.mauritius.tinkerbell_security.repository.AuthUserRepository;
import org.mauritius.tinkerbell_security.service.spi.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;

/**
 * Created by nn_liu on 2017/3/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(AuthUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuthRoles(new HashSet<AuthRole>(authRoleRepository.findAll()));

        user.setDataChange_LastTime(user.getDataChange_LastTime() == null?new Timestamp(System.currentTimeMillis()):user.getDataChange_LastTime());

        authUserRepository.save(user);
    }

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepository.findByUserName(username);
    }
}
