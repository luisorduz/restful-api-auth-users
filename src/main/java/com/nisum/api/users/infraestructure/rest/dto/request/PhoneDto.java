package com.nisum.api.users.infraestructure.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneDto {

    private String number;

    private String cityCode;

    private String contryCode;
}
