package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {



    @PostMapping("/register")
    public String registrer(@RequestBody UserEntity user) {
        return "registered";
    }

    @PostMapping("/auth")
    public String login(@RequestBody UserEntity user) {
        return "authenticated";
    }

}
