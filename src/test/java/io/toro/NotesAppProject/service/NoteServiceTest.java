package io.toro.NotesAppProject.service;

import static io.toro.NotesAppProject.utils.RestControllerUtils.mapToJson;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import io.toro.NotesAppProject.domain.Note;

public class NoteServiceTest {

    private List<Note> notes = new ArrayList<>();

    @Before
    public void setUp(){
        notes.add( new Note( 1L, "title1", "content1", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 2L, "title2", "content3", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 3L, "title3", "content3", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 4L, "title4", "content3", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 5L, "asda", "c411232", new Date().toString(), new Date().toString() ));
    }

    @Test
    public void findAllNotes() throws Exception {
        int result = notes.size();
        assertEquals("fail: must not be null", 5, result);
    }

    @Test
    public void addNote() throws Exception {
        notes.add( new Note( 6L, "title6", "content6", new Date().toString(), new Date().toString() ));
        int result = notes.size();
        assertEquals("fail: not added", 6 , result);
    }

    @Test
    public void deleteNote() throws Exception {
        notes.remove( 1 );
        int result = notes.size();
        assertEquals( "fail: not deleted", 4, result );
    }

    @Test
    public void updateNote() throws Exception {
        Note sampleNoteData = new Note( 5L, "edited", "edited", new Date().toString() , new Date().toString());
        notes.set( 4, sampleNoteData );
        assertEquals( notes.get( 4 ), sampleNoteData );
    }

    @Test
    public void findOneNote() throws Exception {
        String expectedResponseBody = mapToJson( new Note( 1L, "title1", "content1", new Date().toString(), new Date().toString()));
        String actualResponseBody = mapToJson( notes.get(0) );
        assertEquals( "fail: response body not equal", expectedResponseBody, actualResponseBody );
    }

    @Test
    public void findAllTitleLike() throws Exception {
        List<Note> noteTitleLike = notes.stream().filter( n -> n.getTitle().contains( "tit" )).collect( Collectors.toList());
        assertEquals( "fail: content size not equal", 4, noteTitleLike.size() );
    }

    @Test
    public void findAllContentLike() throws Exception {
        List<Note> noteTitleLike = notes.stream().filter( n -> n.getContent().contains( "tent" )).collect( Collectors.toList());
        assertEquals( "fail: content size not equal", 4, noteTitleLike.size() );
    }
}
