package com.nisum.api.users.infraestructure.rest.controller;

import com.nisum.api.users.application.service.UserService;
import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.config.security.jwt.JwtUtils;
import com.nisum.api.users.infraestructure.rest.dto.request.LoginRequest;
import com.nisum.api.users.infraestructure.rest.dto.response.ApiErrorResponse;
import com.nisum.api.users.infraestructure.rest.dto.response.JwtResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthControllerTest.class)
class AuthControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {

        // Datos de entrada
        loginRequest = new LoginRequest("andresorduz1@gmail.com", "*Nisum2023");

        // Configuración inicial para cada prueba
        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Andres");
        user.setEmail("Andresorduz1@gmail.com");
        user.setPassword("*Nisum2023");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuMTIyMjEyMkByb2RyaWd1ZXoub3JnIiwiaWF0IjoxNjgwMjgxODA1LCJleHAiOjE2ODAzNjgyMDV9.x-xZojKxM8xPpU2Sl4R7Go5Z6F82qoLfKiSycZre7UMtnVOUz6pRBLQOs0qu6tGLsR0tRmGd29WgA6IFiPoLgw");
        user.setIsActive(true);
    }

    @Test
    void testAuthenticateUserSuccess() {

        String jwt = "jwt_token";
        LocalDateTime lastLogin = LocalDateTime.now();

        // Mock del AuthenticationManager
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())))
                .thenReturn(mock(Authentication.class));

        // Mock del JwtUtils
        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn(jwt);

        // Mock del UserService
        when(userService.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(userService.updateLastLoginByEmail(lastLogin,loginRequest.getEmail())).thenReturn(any(Integer.class));

        // Llamada al endpoint
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Verificación de los resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals(loginRequest.getEmail(), jwtResponse.getEmail());
        assertNotNull(jwtResponse.getLastLogin());
        assertNotNull(jwtResponse.getToken());
    }

    @Test
    void testAuthenticateUserNotFound() {

        // Mock del AuthenticationManager
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("User not found"));

        // Llamada al endpoint
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Verificación de los resultados
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiErrorResponse apiErrorResponse = (ApiErrorResponse) response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, apiErrorResponse.getStatus());
        assertEquals("User not found", apiErrorResponse.getMessage());
    }

}