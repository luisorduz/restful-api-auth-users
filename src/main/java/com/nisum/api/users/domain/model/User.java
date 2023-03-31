package com.nisum.api.users.domain.model;

import com.nisum.api.users.infraestructure.springdata.entity.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private String token;

    private Boolean isActive;

    private List<PhoneEntity> phones = new ArrayList<>();

}
