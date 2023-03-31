package com.nisum.api.users.infraestructure.springdata.mapper;

import com.nisum.api.users.domain.model.Phone;
import com.nisum.api.users.infraestructure.springdata.entity.PhoneEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapperDbo.class})
public interface PhoneMapperDbo {


    @Mappings({@Mapping(source = "id", target = "id"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "cityCode", target = "cityCode"),
            @Mapping(source = "userEntity", target = "userEntity")})
    PhoneEntity toDbo(Phone phone);

    @InheritInverseConfiguration
    Phone toDomain(PhoneEntity phoneEntity);

}
