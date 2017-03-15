package org.mauritius.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.mauritius.tinkerbell_security.entity.bo.PermBean;
import org.mauritius.tinkerbell_security.entity.po.AuthPermission;
import org.mauritius.tinkerbell_security.mapper.PermissionMapper;

/**
 * Created by nn_liu on 2017/3/15.
 */

public class AuthMapperTest {

    @Test
    public void testUserMapper() {

    }

    @Test
    public void testRoleMapper() {

    }

    @Test
    public void testPermissionMapper() {

        AuthPermission authPermission = new AuthPermission();
        authPermission.setPermissionName("luffy");
        authPermission.setDescription("test for perm");
        PermBean permBean = PermissionMapper.MAPPER.toPerm(authPermission);

        Assert.assertEquals("luffy", permBean.getPermName());
    }
}
