package com.notes.notesdemo.model.response;


import com.notes.notesdemo.model.entity.UserEntity;
import lombok.Data;

@Data
public class UserRegisterResponse {

    private String message;
    private UserEntity user;

}
