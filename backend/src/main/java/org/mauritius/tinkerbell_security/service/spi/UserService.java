package org.mauritius.tinkerbell_security.service.spi;

import org.mauritius.tinkerbell_security.entity.po.AuthUser;

/**
 * Created by nn_liu on 2017/3/9.
 */
public interface UserService {

    void save(AuthUser user);

    AuthUser findByUsername(String username);
}
