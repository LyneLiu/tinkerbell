package org.tinkerbell.security.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tinkerbell.TinkerbellCommonApplication;
import org.tinkerbell.TinkerbellSecurityApplication;
import org.tinkerbell.security.entity.po.AuthPermission;
import org.tinkerbell.security.repository.AuthPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nn_liu on 2017/3/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellSecurityApplication.class})
@EnableAutoConfiguration
public class AuthPermissionRepoTest {

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Test
    public void testAuthUserRepo(){
        AuthPermission permission = authPermissionRepository.findByPermissionName("edit");
        Assert.assertEquals("edit",permission.getDescription());
    }

}
