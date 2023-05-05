package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.request.NoteDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @GetMapping
    public String getNotesByUserId(){
        return "notes";
    }

    @PostMapping
    public String saveNote(@RequestBody @Valid NoteDTO note) {
        return "saved";
    }
}
