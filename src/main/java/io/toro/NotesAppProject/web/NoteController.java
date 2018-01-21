package io.toro.NotesAppProject.web;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.toro.NotesAppProject.domain.Note;
import io.toro.NotesAppProject.service.NoteService;


@Api(description = "API for notes")
@RequestMapping("/api/v1/notes")
@RestController
public class NoteController {


    private NoteService noteService;

    @Autowired
    public NoteController( NoteService noteService ) {
        this.noteService = noteService;
    }

    @ApiOperation( "find all notes" )
    @GetMapping(value = "")
    public ResponseEntity<List<Note>> findAllNotes() {
        List<Note> notes = noteService.findAllNotes();
        Boolean isNoteEmpty = notes.toString().equals( "[]" );
        ResponseEntity noteEmpty = new ResponseEntity( HttpStatus.NO_CONTENT );
        ResponseEntity<List<Note>> noteNotEmpty = new ResponseEntity<>(notes, HttpStatus.FOUND );
        return isNoteEmpty ? noteEmpty: noteNotEmpty;
    }

    @ApiOperation( "pageable notes , sort by title DESC" )
    @GetMapping(value = "/pageable")
    public  ResponseEntity<List<Note>> findAllNotesPageable(@RequestParam int page, @RequestParam int size){
        List<Note> notes = noteService.findAllNotesPageable( new PageRequest( page,size,Direction.DESC,"title" ) );
        return new ResponseEntity<>( notes, HttpStatus.FOUND );
    }

    @ApiOperation( "find one note by id" )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Note> findOneNote(@PathVariable Long id){
        Note note = noteService.findOneNote( id );
        Boolean isNoteEmpty = note == null;
        ResponseEntity noteEmpty = new ResponseEntity( HttpStatus.NO_CONTENT );
        ResponseEntity<Note> noteNotEmpty = new ResponseEntity<>( note, HttpStatus.FOUND );
        return isNoteEmpty ? noteEmpty : noteNotEmpty ;
    }

    @ApiOperation( "add note" )
    @PostMapping(value = "")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        note.setCreatedAt(new Date().toString());
        note.setUpdatedAt(new Date().toString());
        Note result = noteService.addNote(note);
        Boolean isNoteFound = findOneNote(result.getId() ) != null;
        ResponseEntity<Note> noteFound = new ResponseEntity<>(result,HttpStatus.CREATED);
        ResponseEntity noteNotFound = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return isNoteFound ? noteFound : noteNotFound;
    }

    @ApiOperation( "update note" )
    @PutMapping(value = "/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note){
        note.setId( id);
        note.setUpdatedAt( new Date().toString() );
        Boolean isNoteDoesntExist = noteService.findOneNote( id ) == null;
        ResponseEntity noteDoesntExist = new ResponseEntity<>(  HttpStatus.BAD_REQUEST);
        return isNoteDoesntExist? noteDoesntExist : noteExistThenUpdate( id , note );
    }
    private ResponseEntity<Note> noteExistThenUpdate(Long id, @RequestBody Note note ) {
        String createdAt = noteService.findOneNote( id ).getCreatedAt();
        note.setCreatedAt( createdAt );
        noteService.updateNote( note );
        return new ResponseEntity<>(note, HttpStatus.OK );
    }


    @ApiOperation( "delete note" )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNote(@PathVariable Long id){
        Boolean isNoteExist = noteService.findOneNote(id) != null;
        ResponseEntity returnStatusCode = new ResponseEntity( HttpStatus.BAD_REQUEST );
        return isNoteExist ? noteExistThenDelete(id) : returnStatusCode;
    }
    private ResponseEntity noteExistThenDelete(Long id) {
        noteService.deleteNote( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @ApiOperation( "find all note with the title like" )
    @GetMapping(value = "/title/{title}")
    public ResponseEntity<List<Note>> findAllTitleLike(@PathVariable String title){
        List<Note> notes = noteService.findAllTitleLike( title );
        Boolean isNoteEmpty = notes.toString().equals( "[]" );
        ResponseEntity noteEmpty = new ResponseEntity( HttpStatus.NO_CONTENT);
        ResponseEntity<List<Note>> noteNotEmpty = new ResponseEntity<>(notes, HttpStatus.FOUND);
        return isNoteEmpty ? noteEmpty : noteNotEmpty;
    }

    @ApiOperation( "find all note with the content like" )
    @GetMapping(value = "/content/{content}")
    public ResponseEntity<List<Note>> findAllContentLike(@PathVariable String content){
        List<Note> notes = noteService.findAllContentLike( content );
        Boolean isNoteEmpty = notes.toString().equals( "[]" );
        ResponseEntity noteEmpty = new ResponseEntity( HttpStatus.NO_CONTENT);
        ResponseEntity<List<Note>> noteNotEmpty = new ResponseEntity<>(notes, HttpStatus.FOUND);
        return isNoteEmpty ? noteEmpty : noteNotEmpty;
    }
}
