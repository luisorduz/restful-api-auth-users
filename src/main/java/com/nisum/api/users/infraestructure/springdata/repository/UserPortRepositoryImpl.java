package com.nisum.api.users.infraestructure.springdata.repository;

import com.nisum.api.users.application.service.UserPortRepository;
import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.springdata.entity.UserEntity;
import com.nisum.api.users.infraestructure.springdata.mapper.UserMapperDbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserPortRepositoryImpl implements UserPortRepository {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapperDbo userMapperDbo;

    @Override
    public User register(User user) {

        UserEntity userEntity = userMapperDbo.toDbo(user);

        if (userEntity.getPhones() != null) {
            userEntity.getPhones().forEach(phoneEntity -> {
                phoneEntity.setUserEntity(userEntity);
            });
        }
        return userMapperDbo.toDomain(userRepository.save(userEntity));
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public int updateLastLoginByEmail(LocalDateTime lastLogin, String email) {
        return userRepository.updateLastLoginByEmail(lastLogin, email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMapperDbo.toDomain(userRepository.findByEmail(email).orElse(null)));
    }

}
