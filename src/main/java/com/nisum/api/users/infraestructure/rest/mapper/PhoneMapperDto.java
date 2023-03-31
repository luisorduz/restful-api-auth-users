package com.nisum.api.users.infraestructure.rest.mapper;

import com.nisum.api.users.domain.model.Phone;
import com.nisum.api.users.infraestructure.rest.dto.request.PhoneDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PhoneMapperDto {

    @Mappings({@Mapping(source = "number", target = "number"),
            @Mapping(source = "cityCode", target = "cityCode"),
            @Mapping(source = "contryCode", target = "contryCode")})
    Phone toDomain(PhoneDto phoneDto);

    @InheritInverseConfiguration
    PhoneDto toDto(Phone phone);

}
