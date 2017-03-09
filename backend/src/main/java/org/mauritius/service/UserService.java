package org.mauritius.service;

import org.mauritius.domain.tinkerbell.AuthUser;

/**
 * Created by nn_liu on 2017/3/9.
 */
public interface UserService {

    void save(AuthUser user);

    AuthUser findByUsername(String username);
}
