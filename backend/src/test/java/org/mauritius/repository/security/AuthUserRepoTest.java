package org.mauritius.repository.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.tinkerbell_security.entity.po.AuthRole;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.repository.AuthRoleRepository;
import org.mauritius.tinkerbell_security.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by nn_liu on 2017/3/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class AuthUserRepoTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Test
    @Commit
    public void testAuthUserRepo(){
        AuthUser user = authUserRepository.findByUserName("lyne");
        AuthRole role = authRoleRepository.findByRoleName("admin");
        List<AuthRole> roles = user.getAuthRoles();
        roles.add(role);
        authUserRepository.save(user);
        Assert.assertEquals("1qaz2wsx",user.getPassword().toLowerCase());
        Assert.assertEquals(1,roles.size());
    }

    @Test
    public void testAuthUserRepoForRole(){

        AuthUser user = authUserRepository.findByUserName("lyne");

        user.getAuthRoles().removeIf(authRole -> authRole.getRoleName().equalsIgnoreCase("admin"));

        Assert.assertEquals("lyne",user.getUserName());
    }

    @Test
    public void testAuthUserRepoForPerm(){

    }

}
