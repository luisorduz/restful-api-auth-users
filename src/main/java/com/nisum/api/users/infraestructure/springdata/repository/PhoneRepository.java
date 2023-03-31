package com.nisum.api.users.infraestructure.springdata.repository;

import com.nisum.api.users.infraestructure.springdata.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
