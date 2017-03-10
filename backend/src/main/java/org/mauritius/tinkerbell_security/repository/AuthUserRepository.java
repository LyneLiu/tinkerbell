package org.mauritius.tinkerbell_security.repository;

import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/3/8.
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    AuthUser findByUserName(String username);

}
