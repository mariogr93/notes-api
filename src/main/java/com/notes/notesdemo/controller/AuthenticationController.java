package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.UserLoginDTO;
import com.notes.notesdemo.model.request.UserRegisterDTO;
import com.notes.notesdemo.model.response.GeneralResponse;
import com.notes.notesdemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public GeneralResponse registrer(@RequestBody @Valid UserRegisterDTO user) {
        return GeneralResponse.builder().data(Map.of("user", this.authService.registerUser(user))).build();
    }

    @PostMapping("/login")
    public GeneralResponse login(@RequestBody @Valid UserLoginDTO user) {
        return GeneralResponse.builder().data(Map.of("user", this.authService.loginUser(user))).build();
    }

}
