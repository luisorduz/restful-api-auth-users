package com.nisum.api.users.infraestructure.rest.controller;

import com.nisum.api.users.application.service.UserService;
import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.config.security.jwt.JwtUtils;
import com.nisum.api.users.infraestructure.config.security.services.UserDetailsImpl;
import com.nisum.api.users.infraestructure.rest.dto.response.ApiErrorResponse;
import com.nisum.api.users.infraestructure.rest.dto.response.JwtResponse;
import com.nisum.api.users.infraestructure.rest.dto.request.LoginRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    @ApiOperation("Login with email and password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Login details not working") }
    )
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Clock clock = Clock.system(ZoneId.of("America/Bogota"));
            LocalDateTime today = LocalDateTime.now(clock);

            Optional<User> user = userService.findByEmail(loginRequest.getEmail());
            LocalDateTime localDateTime = Optional.ofNullable(user.get().getLastLogin()).orElse(today);

            userService.updateLastLoginByEmail(today, loginRequest.getEmail());

            return ResponseEntity.ok(new JwtResponse(
                    loginRequest.getEmail(),
                    localDateTime,
                    jwt
            ));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
