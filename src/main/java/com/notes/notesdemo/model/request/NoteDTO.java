package com.notes.notesdemo.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class NoteDTO {
    @NotBlank(message = "The  title is required.")
    private String title;
    @NotBlank(message = "The  description is required.")
    private String description;
    @NotNull(message = "The  completion status is required.")
    private Boolean completed;
    @NotBlank(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String userName;
}
