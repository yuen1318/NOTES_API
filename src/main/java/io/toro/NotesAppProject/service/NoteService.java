package io.toro.NotesAppProject.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.toro.NotesAppProject.domain.Note;
import io.toro.NotesAppProject.repository.NoteRepository;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService( NoteRepository noteRepository ) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAllNotes(){
        List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach( notes :: add );
        return notes;
    }

    public Note addNote(Note note){
        return noteRepository.save( note );
    }

    public void deleteNote(Long id){
        noteRepository.delete( id );
    }

    public void updateNote(Note note){
        noteRepository.save( note );
    }

    public Note findOneNote( Long id){
        return noteRepository.findOne(  id  );
    }

    public List<Note> findAllTitleLike(String title){
        return noteRepository.findAllNoteByTitleLikeIgnoreCaseOrderByIdAsc( "%"+title+"%" );
    }

    public List<Note> findAllContentLike(String content){
        return noteRepository.findAllNoteByContentLikeIgnoreCaseOrderByIdDesc( "%"+content+"%" );
    }

    public List<Note> findAllNotesPageable(PageRequest pageRequest){
        List<Note> notes = new ArrayList<>();
        notes.addAll( noteRepository.findAll( pageRequest ) );
        return notes;

    }


}
