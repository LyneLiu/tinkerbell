package org.mauritius.tinkerbell_security.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mauritius.tinkerbell_security.entity.bo.UserBean;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;

/**
 * Created by nn_liu on 2017/3/13.
 */
@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "userName",target = "userName"),
            @Mapping(source = "password",target = "password")
    })
    UserBean toUser(AuthUser authRole);

    @InheritInverseConfiguration
    AuthUser fromUser(UserBean roleBean);
}
