package org.mauritius.repository.tinkerbell;

import org.mauritius.entity.po.tinkerbell.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/3/8.
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    AuthUser findByUserName(String username);

}
