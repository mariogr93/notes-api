package com.notes.notesdemo.service;

import com.notes.notesdemo.config.security.JwtService;
import com.notes.notesdemo.config.security.UserDetailsImpl;
import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.UserLoginDTO;
import com.notes.notesdemo.model.request.UserRegisterDTO;
import com.notes.notesdemo.model.response.UserLoginResponse;
import com.notes.notesdemo.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserEntity registerUser(UserRegisterDTO userRegisterDTO) {
        if(this.authRepository.existsByEmail(userRegisterDTO.getEmail())){
            throw new IllegalStateException("Email already registered");
        }

        UserEntity newUser = userRegisterDTO.createUserEntity();
        newUser.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
        UserEntity registeredUser = this.authRepository.save(newUser);
        registeredUser.setPassword("");
        return  registeredUser;
    }

    public UserLoginResponse loginUser(UserLoginDTO userLoginDTO) {

        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
        UserDetailsImpl authenticatedUser = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = this.authRepository.findByEmail(authenticatedUser.getUsername()).get();
        String token = this.jwtService.generateToken(authenticatedUser);

        return UserLoginResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .token(token)
                .role(user.getRole())
                .build();
    }

}
