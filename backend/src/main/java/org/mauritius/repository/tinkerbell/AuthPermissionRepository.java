package org.mauritius.repository.tinkerbell;

import org.mauritius.entity.po.tinkerbell.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/3/8.
 */
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {

    AuthPermission findByPermissionName(String rolename);
    
}
