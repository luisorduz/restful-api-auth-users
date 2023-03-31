package com.nisum.api.users.infraestructure.rest.mapper;

import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.rest.dto.request.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {PhoneMapperDto.class})
public interface UserMapperDto {

    @Mappings({@Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "phones", target = "phones")})
    User toDomain(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toDto(User user);
}
