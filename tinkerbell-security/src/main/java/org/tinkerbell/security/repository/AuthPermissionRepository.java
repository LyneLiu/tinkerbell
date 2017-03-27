package org.tinkerbell.security.repository;

import org.tinkerbell.security.entity.po.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/3/8.
 */
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {

    AuthPermission findByPermissionName(String rolename);
    
}
