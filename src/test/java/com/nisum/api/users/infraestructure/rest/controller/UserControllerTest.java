package com.nisum.api.users.infraestructure.rest.controller;

import com.nisum.api.users.application.service.UserService;
import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.rest.dto.request.UserDto;
import com.nisum.api.users.infraestructure.rest.dto.response.UserResponse;
import com.nisum.api.users.infraestructure.rest.mapper.UserMapperDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthControllerTest.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserMapperDto userMapperDto;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testRegister() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        UserResponse userResponse = new UserResponse(user.getId(), user.getCreated(),
                user.getModified(), user.getLastLogin(), user.getToken(), user.getIsActive());

        when(userService.emailExists(userDto.getEmail())).thenReturn(false);
        when(userMapperDto.toDomain(userDto)).thenReturn(user);
        when(encoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userService.register(any(User.class))).thenReturn(user);

        // When
        ResponseEntity<Object> responseEntity = userController.register(userDto);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testRegisterWithExistingEmail() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setName("Andres");
        userDto.setEmail("andresorduz1@gmail.com");
        userDto.setPassword("*Nisum2023");

        when(userService.emailExists(userDto.getEmail())).thenReturn(true);

        // When
        ResponseEntity<Object> responseEntity = userController.register(userDto);

        // Then
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

}