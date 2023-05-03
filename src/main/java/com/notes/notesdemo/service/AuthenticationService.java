package com.notes.notesdemo.service;

import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private AuthenticationRepository authenticationRepository;

    public UserEntity registerUser(UserEntity user) {
        return this.authenticationRepository.save(user);
    }

}
