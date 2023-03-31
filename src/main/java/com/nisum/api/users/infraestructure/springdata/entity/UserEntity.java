package com.nisum.api.users.infraestructure.springdata.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime created;

    private LocalDateTime modified;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    private String token;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhoneEntity> phones;

}
