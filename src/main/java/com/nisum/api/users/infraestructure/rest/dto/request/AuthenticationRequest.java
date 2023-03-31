package com.nisum.api.users.infraestructure.rest.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
