package com.nisum.api.users.infraestructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String email;

    private LocalDateTime lastLogin;

    private String token;

}

