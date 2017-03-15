package org.mauritius.repository.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.tinkerbell_security.entity.po.AuthRole;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by nn_liu on 2017/3/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class AuthUserRepoTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Test
    public void testAuthUserRepo(){
        AuthUser user = authUserRepository.findByUserName("lyne");
        List<AuthRole> roleSet = user.getAuthRoles();
        Assert.assertEquals("1qaz2wsx",user.getPassword().toLowerCase());
        Assert.assertEquals(1,roleSet.size());
    }

}
