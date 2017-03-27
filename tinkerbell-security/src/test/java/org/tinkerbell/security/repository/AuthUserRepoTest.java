package org.tinkerbell.security.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tinkerbell.TinkerbellCommonApplication;
import org.tinkerbell.TinkerbellSecurityApplication;
import org.tinkerbell.security.entity.po.AuthRole;
import org.tinkerbell.security.entity.po.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by nn_liu on 2017/3/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellSecurityApplication.class})
@EnableAutoConfiguration
public class AuthUserRepoTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    @Qualifier("entityManagerSecurity")
    private EntityManager entityManagerSecurity;

    @Test
    @Commit
    public void testAuthUserRepo() {
        AuthUser user = authUserRepository.findByUserName("lyne");
        AuthRole role = authRoleRepository.findByRoleName("admin");
        List<AuthRole> roles = user.getAuthRoles();
        roles.add(role);
        authUserRepository.save(user);
        Assert.assertEquals("1qaz2wsx", user.getPassword().toLowerCase());
        Assert.assertEquals(1, roles.size());
    }

    @Test
    public void testAuthUserRepoForRole() {

        EntityTransaction txn = entityManagerSecurity.getTransaction();

        try {

            txn.begin();

            AuthUser user = authUserRepository.findByUserName("lyne");

            user.getAuthRoles().removeIf(authRole -> authRole.getRoleName().equalsIgnoreCase("admin"));

            entityManagerSecurity.merge(user);

            txn.commit();

            Assert.assertEquals("lyne", user.getUserName());

        } catch (Exception e) {
            // do nothing
        }


    }

    @Test
    public void testAuthUserRepoForPerm() {

    }

}
