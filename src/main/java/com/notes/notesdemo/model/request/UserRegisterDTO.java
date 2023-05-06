package com.notes.notesdemo.model.request;


import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.enums.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterDTO {

    @NotBlank(message = "The firstName is required.")
    private String firstName;
    @NotBlank(message = "The lastName is required.")
    private String lastName;
    @NotBlank(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;
    @NotBlank(message = "The  password is required.")
    private String password;
    @NotNull(message = "The  role is required.")
    private Role role;

    public UserEntity createUserEntity() {
        return new UserEntity(getFirstName(), getLastName(), getEmail(), getPassword(), getRole());
    }
}
