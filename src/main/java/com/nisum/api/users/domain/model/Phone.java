package com.nisum.api.users.domain.model;

import com.nisum.api.users.infraestructure.springdata.entity.UserEntity;
import lombok.Data;

@Data
public class Phone {

    private Long id;

    private String number;

    private String cityCode;

    private String contryCode;

    private UserEntity userEntity;

}
