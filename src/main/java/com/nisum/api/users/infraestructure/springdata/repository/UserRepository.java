package com.nisum.api.users.infraestructure.springdata.repository;

import com.nisum.api.users.infraestructure.springdata.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.lastLogin = ?1 where u.email = ?2")
    int updateLastLoginByEmail(LocalDateTime lastLogin, String email);


}