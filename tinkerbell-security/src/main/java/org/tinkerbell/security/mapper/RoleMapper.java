package org.tinkerbell.security.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.tinkerbell.security.entity.bo.RoleBean;
import org.tinkerbell.security.entity.po.AuthRole;

/**
 * Created by nn_liu on 2017/3/13.
 */

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    @Mappings({
            @Mapping(source = "roleName", target = "roleName"),
            @Mapping(source = "authPerms", target = "perms")
    })
    RoleBean toRole(AuthRole authRole);

    @InheritInverseConfiguration
    AuthRole fromRole(RoleBean roleBean);
}
