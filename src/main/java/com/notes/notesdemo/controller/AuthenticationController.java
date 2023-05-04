package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.UserLoginDTO;
import com.notes.notesdemo.model.request.UserRegisterDTO;
import com.notes.notesdemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authService;

    @PostMapping("/register")
    public UserEntity registrer(@RequestBody @Valid UserRegisterDTO user) {
        return this.authService.registerUser(user);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody @Valid UserLoginDTO user) {
        return this.authService.loginUser(user);
    }

}
