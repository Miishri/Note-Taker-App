package com.Notes.Controller;


import com.Notes.NoteApplication;
import com.Notes.NoteRepository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/notes")
public class NoteRestController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteRestController(NoteRepository noteRepo) {
        this.noteRepository = noteRepo;
    }

}
