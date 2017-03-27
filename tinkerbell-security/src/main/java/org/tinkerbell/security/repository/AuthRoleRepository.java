package org.tinkerbell.security.repository;

import org.tinkerbell.security.entity.po.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/3/8.
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {

    AuthRole findByRoleName(String rolename);
    
}
