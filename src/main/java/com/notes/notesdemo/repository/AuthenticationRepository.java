package com.notes.notesdemo.repository;

import com.notes.notesdemo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
