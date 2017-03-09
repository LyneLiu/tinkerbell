package org.mauritius.repository.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.entity.po.tinkerbell.AuthPermission;
import org.mauritius.entity.po.tinkerbell.AuthRole;
import org.mauritius.repository.tinkerbell.AuthRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by nn_liu on 2017/3/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class AuthRoleRepoTest {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Test
    public void testAuthUserRepo(){
        AuthRole role = authRoleRepository.findByRoleName("admin");
        Set<AuthPermission> permissionSet = role.getAuthPerms();
        Assert.assertEquals("admin",role.getDescription());
        Assert.assertEquals(2,permissionSet.size());
    }

}
