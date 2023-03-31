package com.nisum.api.users.infraestructure.config.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo("Nisum Rest API",
                "APIs for Nisum.",
                "1.0",
                "Terms of service",
                new Contact("Luis Andres Orduz", "www.creaduz.com", "andresorduz1@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.nisum.api.users.infraestructure.rest.controller"))
                .build();
    }


}