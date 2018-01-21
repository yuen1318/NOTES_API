package io.toro.NotesAppProject.repository;


import java.util.List;



import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.toro.NotesAppProject.domain.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {
    List<Note> findAllNoteByTitleLikeIgnoreCaseOrderByIdAsc(String title);
    List<Note> findAllNoteByContentLikeIgnoreCaseOrderByIdDesc(String content);
    List<Note> findAll(Pageable pageable);
}
