package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.UserLoginDTO;
import com.notes.notesdemo.model.request.UserRegisterDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {



    @PostMapping("/register")
    public String registrer(@Valid @RequestBody UserRegisterDTO user) {
        return "registered";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDTO user) {
        return "authenticated";
    }

}
