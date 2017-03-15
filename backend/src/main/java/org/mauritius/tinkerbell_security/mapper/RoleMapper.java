package org.mauritius.tinkerbell_security.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mauritius.tinkerbell_security.entity.bo.RoleBean;
import org.mauritius.tinkerbell_security.entity.po.AuthRole;

/**
 * Created by nn_liu on 2017/3/13.
 */

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    @Mappings({
        @Mapping(source = "roleName",target = "roleName")
    })
    RoleBean toRole(AuthRole authRole);

    @InheritInverseConfiguration
    AuthRole fromRole(RoleBean roleBean);
}
