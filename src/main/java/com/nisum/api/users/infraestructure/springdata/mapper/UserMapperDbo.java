package com.nisum.api.users.infraestructure.springdata.mapper;

import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.springdata.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserMapperDbo {

    @Mappings({@Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "created", target = "created"),
            @Mapping(source = "modified", target = "modified"),
            @Mapping(source = "lastLogin", target = "lastLogin"),
            @Mapping(source = "token", target = "token"),
            @Mapping(source = "phones", target = "phones")})
    User toDomain(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toDbo(User user);
}
