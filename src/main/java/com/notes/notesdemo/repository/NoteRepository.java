package com.notes.notesdemo.repository;

import com.notes.notesdemo.model.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {

    @Query("""
    Select n from NoteEntity n inner join UserEntity u on n.user.id = u.id where u.id = :userId
    """)
    List<NoteEntity> findAllByUserId(Integer userId);
}
