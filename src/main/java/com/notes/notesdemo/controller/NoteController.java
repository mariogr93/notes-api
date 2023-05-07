package com.notes.notesdemo.controller;


import com.notes.notesdemo.model.request.NoteDTO;
import com.notes.notesdemo.model.response.GeneralResponse;
import com.notes.notesdemo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping(name = "/user/{userId}")
    public ResponseEntity<GeneralResponse> getNotesByUserId(@PathVariable Integer userId){

        return ResponseEntity.ok(GeneralResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Registered notes")
                .data(Map.of("notes", this.noteService.getAllUserNotes(userId)))
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<GeneralResponse>  saveNote(@RequestBody @Valid NoteDTO note) {

        return ResponseEntity.ok(GeneralResponse.builder()
                .timeStamp(now())
                .data(Map.of("note", this.noteService.saveNote(note)))
                .message("Note saved")
                .statusCode(200)
                .status(HttpStatus.CREATED)
                .build()
        );
    }
}
