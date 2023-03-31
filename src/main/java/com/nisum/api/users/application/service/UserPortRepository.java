package com.nisum.api.users.application.service;

import com.nisum.api.users.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserPortRepository {

    User register(User user);

    boolean emailExists(String email);

    int updateLastLoginByEmail(LocalDateTime lastLogin, String email);

    Optional<User> findByEmail(String email);
}
