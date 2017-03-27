package org.tinkerbell.security.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tinkerbell.TinkerbellCommonApplication;
import org.tinkerbell.TinkerbellSecurityApplication;
import org.tinkerbell.security.entity.po.AuthPermission;
import org.tinkerbell.security.entity.po.AuthRole;
import org.tinkerbell.security.repository.AuthRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellSecurityApplication.class})
@EnableAutoConfiguration
public class AuthRoleRepoTest {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Test
    public void testAuthUserRepo(){
        AuthRole role = authRoleRepository.findByRoleName("admin");
        List<AuthPermission> permissionSet = role.getAuthPerms();
        Assert.assertEquals("admin",role.getDescription());
        Assert.assertEquals(2,permissionSet.size());
    }

}
