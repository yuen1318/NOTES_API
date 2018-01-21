package io.toro.NotesAppProject.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.toro.NotesAppProject.domain.Note;
import io.toro.NotesAppProject.service.NoteService;

import com.github.javafaker.Faker;

@Component
@Profile("dev")
public class DummyData {

    private NoteService noteService;

    private Faker faker = new Faker();

    @Autowired
    public DummyData( NoteService noteService ) {
        this.noteService = noteService;
    }

    @PostConstruct
    public void createDummyData(){
        iterateDummyData( 10 );
    }

    public void iterateDummyData(int numberOfData){
        for ( int i=0; i < numberOfData; i++ ){
            noteService.addNote( new Note(faker.book().title()
                    ,faker.lorem().paragraph()
                    ,new Date().toString()
                    ,new Date().toString()));
        }
    }
}
