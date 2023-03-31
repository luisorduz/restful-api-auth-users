package com.nisum.api.users.application.service;

import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.config.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    private final UserPortRepository userPortRepository;
    public User register(User user){

        Clock clock = Clock.system(ZoneId.of("America/Bogota"));
        LocalDateTime today = LocalDateTime.now(clock);
        user.setCreated(today);
        user.setModified(today);
        user.setLastLogin(today);
        user.setIsActive(true);
        user.setToken(jwtUtils.generateJwtTokenWithoutAuth(user.getEmail()));

        return userPortRepository.register(user);
    }

    public boolean emailExists(String email){
        return userPortRepository.emailExists(email);
    }

    public int updateLastLoginByEmail(LocalDateTime lastLogin, String email){
        return userPortRepository.updateLastLoginByEmail(lastLogin, email);
    }

    public Optional<User> findByEmail(String email) {
        return userPortRepository.findByEmail(email);
    }
}
