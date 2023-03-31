package com.nisum.api.users.infraestructure.rest.controller;

import com.nisum.api.users.application.service.UserService;
import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.rest.mapper.UserMapperDto;
import com.nisum.api.users.infraestructure.rest.dto.response.ApiErrorResponse;
import com.nisum.api.users.infraestructure.rest.dto.response.ErrorResponse;
import com.nisum.api.users.infraestructure.rest.dto.request.UserDto;
import com.nisum.api.users.infraestructure.rest.dto.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserMapperDto userMapperDto;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/")
    @ApiOperation("Register a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Incorrect user registration data") }
    )
    public ResponseEntity<Object> register(@Valid @RequestBody UserDto userDto) {

        try {
            if (userService.emailExists(userDto.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErrorResponse("El correo ya registrado"));
            }

            userDto.setId(UUID.randomUUID());
            userDto.setPassword(encoder.encode(userDto.getPassword()));
            User user = userService.register(userMapperDto.toDomain(userDto));

            UserResponse userResponse = new UserResponse(user.getId(),
                    user.getCreated(),
                    user.getModified(),
                    user.getLastLogin(),
                    user.getToken(),
                    user.getIsActive());

            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }


}
