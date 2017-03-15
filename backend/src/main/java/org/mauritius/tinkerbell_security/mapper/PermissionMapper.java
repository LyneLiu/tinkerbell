package org.mauritius.tinkerbell_security.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mauritius.tinkerbell_security.entity.bo.PermBean;
import org.mauritius.tinkerbell_security.entity.po.AuthPermission;

/**
 * Created by nn_liu on 2017/3/13.
 */

@Mapper
public interface PermissionMapper {

    PermissionMapper MAPPER = Mappers.getMapper(PermissionMapper.class);

    @Mappings({
        @Mapping(source = "permissionName",target = "permName")
    })
    PermBean toPerm(AuthPermission authPermission);

    @InheritInverseConfiguration
    AuthPermission from(PermBean permBean);

}
