package com.notes.notesdemo.service;

import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.UserRegisterDTO;
import com.notes.notesdemo.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private AuthenticationRepository authenticationRepository;

    public UserEntity registerUser(UserRegisterDTO userRegisterDTO) {
        UserEntity user = userRegisterDTO.createUserEntity();
        return this.authenticationRepository.save(user);
    }

    public UserEntity loginUser(UserEntity user) {
        return this.authenticationRepository.findByEmail(user.getEmail()).get();
    }

}
