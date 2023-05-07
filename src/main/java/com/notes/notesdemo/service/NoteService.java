package com.notes.notesdemo.service;

import com.notes.notesdemo.model.entity.NoteEntity;
import com.notes.notesdemo.model.entity.UserEntity;
import com.notes.notesdemo.model.request.NoteDTO;
import com.notes.notesdemo.model.response.NoteResponse;
import com.notes.notesdemo.repository.AuthenticationRepository;
import com.notes.notesdemo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final AuthenticationRepository authRepository;
    private final NoteRepository noteRepository;

    public NoteResponse saveNote(NoteDTO noteDTO) {
        UserEntity user = this.authRepository.findByEmail(noteDTO.getUserName()).orElseThrow(() -> new IllegalStateException("Username is not registered"));
        NoteEntity newNote = new NoteEntity(noteDTO.getTitle(), noteDTO.getDescription(), noteDTO.getCompleted(), user);
        NoteEntity savedNote = this.noteRepository.save(newNote);
        return NoteResponse.builder()
                .id(savedNote.getId())
                .title(savedNote.getTitle())
                .description(savedNote.getDescription())
                .completed(savedNote.getCompleted())
                .userId(savedNote.getUser().getId())
                .userName(savedNote.getUser().getEmail())
                .build();
    }

    public List<NoteResponse> getAllUserNotes(Integer userId ) {

        List<NoteEntity> notes = this.noteRepository.findAllByUserId(userId);
        List<NoteResponse> noteResponse = new ArrayList<>();
        notes.forEach((note) -> noteResponse.add(
                        NoteResponse.builder()
                                .id(note.getId())
                                .title(note.getTitle())
                                .description(note.getDescription())
                                .completed(note.getCompleted())
                                .userId(note.getUser().getId())
                                .userName(note.getUser().getEmail())
                                .build()
                )
        );
        return noteResponse;
    }

    @Transactional
    public String deleteNote(Integer userId, Integer noteId) {
        if( this.noteRepository.existsById(noteId)){
            this.noteRepository.deleteById(noteId);
        }
        return "Note Deleted";
    }

}
