package com.notes.notesdemo.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

    private Integer id;
    private String title;
    private String description;
    private Integer userId;
    private String userName;
    private Boolean completed;
}
