package com.nisum.api.users.infraestructure.springdata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "contry_code")
    private String contryCode;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}