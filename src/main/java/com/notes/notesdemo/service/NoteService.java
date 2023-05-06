package com.notes.notesdemo.service;

import com.notes.notesdemo.model.entity.NoteEntity;
import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.NoteDTO;
import com.notes.notesdemo.repository.AuthenticationRepository;
import com.notes.notesdemo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final AuthenticationRepository authRepository;
    private final NoteRepository noteRepository;

    public NoteEntity saveNote(NoteDTO noteDTO) {
        UserEntity user = this.authRepository.findByEmail(noteDTO.getUserName()).orElseThrow(() -> new IllegalStateException("Username is not registered"));
        NoteEntity newNote = noteDTO.createNoteEntity(user);
        return this.noteRepository.save(newNote);
    }

    public List<NoteEntity> getAllUserNotes(Integer userId ) {
        return this.noteRepository.findAllByUserId(userId);
    }

}
