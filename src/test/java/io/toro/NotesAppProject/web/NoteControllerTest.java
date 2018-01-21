package io.toro.NotesAppProject.web;

import static io.toro.NotesAppProject.utils.RestControllerUtils.mapToJson;
import static io.toro.NotesAppProject.utils.RestControllerUtils.performDelete;
import static io.toro.NotesAppProject.utils.RestControllerUtils.performGet;
import static io.toro.NotesAppProject.utils.RestControllerUtils.performPost;
import static io.toro.NotesAppProject.utils.RestControllerUtils.performPut;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.toro.NotesAppProject.domain.Note;
import io.toro.NotesAppProject.service.NoteService;
@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @MockBean
    private NoteService service;

    @Autowired
    private MockMvc mvc;

    private List<Note> notes = new ArrayList<>();

    @Before
    public void setUp(){
        notes.add( new Note( 1L, "title1", "content1", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 2L, "title2", "content3", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 3L, "title3", "content3", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 4L, "title4", "content4", new Date().toString(), new Date().toString() ));
        notes.add( new Note( 5L, "asd123", "cuntent5", new Date().toString(), new Date().toString() ));
    }

    @Test
    public void findAllNotes() throws Exception {
        String expectedResponseBody = mapToJson(notes);
        when(service.findAllNotes()).thenReturn( notes );
        performGet(mvc,"/api/v1/notes",expectedResponseBody);
        verify(service).findAllNotes();
    }

    @Test
    public void findOneNote() throws Exception {
        String expectedResponseBody = mapToJson( notes.get( 0 ) );
        when(service.findOneNote(anyLong())).thenReturn( notes.get( 0 ) );
        performGet(mvc,"/api/v1/notes/1",expectedResponseBody);
        verify(service).findOneNote(anyLong());
    }

    @Test
    public void addNote() throws Exception {
        Note sampleNoteData = new Note( 6L, "title6", "content6", new Date().toString(), new Date().toString());
        notes.add( sampleNoteData );
        String jsonInput = mapToJson(sampleNoteData);
        when(service.addNote(any(Note.class))).thenReturn(sampleNoteData);
        performPost(mvc,"/api/v1/notes",jsonInput, mapToJson(notes.get( 5 )));
        verify(service).addNote(any(Note.class));
    }

    @Test
    public void updateNote() throws Exception {
        Note sampleNoteData = new Note( 1L, "edited", "edited", new Date().toString(), new Date().toString());
        notes.set( 0, sampleNoteData );
        String jsonInput = mapToJson( sampleNoteData );
        when(service.findOneNote(anyLong())).thenReturn(sampleNoteData);
        performPut(mvc,"/api/v1/notes/1",jsonInput);
        verify(service).updateNote(any( Note.class ));
    }

    @Test
    public void deleteNote() throws Exception {
        Note sampleNoteData = notes.get( 0 );
        when(service.findOneNote(anyLong())).thenReturn(sampleNoteData);
        performDelete( mvc, "/api/v1/notes/1");
        verify(service).deleteNote(anyLong());
    }

    @Test
    public void findAllTitleLike() throws Exception {
        List<Note> noteTitleLike = notes.stream().filter( n -> n.getTitle().contains( "tit" )).collect( Collectors.toList());
        when(service.findAllTitleLike( anyString() )).thenReturn(noteTitleLike);
        performGet(mvc, "/api/v1/notes/title/tit", mapToJson( noteTitleLike ));
        verify( service ).findAllTitleLike( anyString() );
    }

    @Test
    public void findAllContentLike() throws Exception {
        List<Note> noteContentLike = notes.stream().filter( n -> n.getContent().contains( "ont" )).collect( Collectors.toList());
        when(service.findAllContentLike( anyString() )).thenReturn(noteContentLike);
        performGet(mvc, "/api/v1/notes/content/con", mapToJson( noteContentLike ));
        verify( service ).findAllContentLike( anyString() );
    }
}
