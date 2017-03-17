package org.mauritius.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.tinkerbell_portal.entity.po.springdemo.User;
import org.mauritius.tinkerbell_security.entity.bo.PermBean;
import org.mauritius.tinkerbell_security.entity.bo.RoleBean;
import org.mauritius.tinkerbell_security.entity.bo.UserBean;
import org.mauritius.tinkerbell_security.entity.po.AuthPermission;
import org.mauritius.tinkerbell_security.entity.po.AuthRole;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.mapper.PermissionMapper;
import org.mauritius.tinkerbell_security.mapper.RoleMapper;
import org.mauritius.tinkerbell_security.mapper.UserMapper;
import org.mauritius.tinkerbell_security.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nn_liu on 2017/3/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class AuthMapperTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Test
    public void testUserMapper() {

        AuthUser authUser = new AuthUser();
        authUser.setUserName("lyne");
        authUser.setDescription("test for user");

        List<AuthRole> authRoles = new ArrayList<>();
        AuthRole authRole = new AuthRole();
        authRole.setRoleName("admin");
        authRole.setDescription("test for role");
        authRoles.add(authRole);

        authUser.setAuthRoles(authRoles);

        UserBean userBean = UserMapper.MAPPER.toUser(authUser);
        List<RoleBean> roleBeans = userBean.getRoles();

        Assert.assertEquals("lyne",userBean.getUserName());
        Assert.assertEquals("admin",roleBeans.get(0).getRoleName());

    }

    @Test
    public void testRoleMapper() {

        AuthRole authRole = new AuthRole();
        authRole.setRoleName("admin");
        authRole.setDescription("test for role");

        List<AuthUser> authUsers = new ArrayList<>();
        AuthUser authUser = new AuthUser();
        authUser.setUserName("lyne");
        authUser.setDescription("test user info");
        authUsers.add(authUser);

        List<AuthPermission> authPermissions = new ArrayList<>();
        AuthPermission authPermission = new AuthPermission();
        authPermission.setPermissionName("zoro");
        authPermission.setDescription("test perm info");
        authPermissions.add(authPermission);

        authRole.setAuthUsers(authUsers);
        authRole.setAuthPerms(authPermissions);

        RoleBean role = RoleMapper.MAPPER.toRole(authRole);
        List<PermBean> permBeans = role.getPerms();

        Assert.assertEquals("admin",role.getRoleName());
        Assert.assertEquals("zoro",permBeans.get(0).getPermName());

    }

    @Test
    public void testPermissionMapper() {

        AuthPermission authPermission = new AuthPermission();
        authPermission.setPermissionName("luffy");
        authPermission.setDescription("test for perm");
        PermBean permBean = PermissionMapper.MAPPER.toPerm(authPermission);

        Assert.assertEquals("luffy", permBean.getPermName());
    }


    @Test
    public void testAuthRepo(){
        AuthUser authUser = authUserRepository.findByUserName("test_test_test");
        UserBean userBean = UserMapper.MAPPER.toUser(authUser);

        Assert.assertEquals("test_test_test",userBean.getUserName());
    }
}
