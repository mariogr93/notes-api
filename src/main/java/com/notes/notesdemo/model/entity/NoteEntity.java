package com.notes.notesdemo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {

    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public NoteEntity(String title, String description, Boolean completed, UserEntity user) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.completed = completed;

    }
}
