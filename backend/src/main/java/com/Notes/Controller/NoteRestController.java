package com.Notes.Controller;

import com.Notes.Model.Note;
import com.Notes.NoteNotFoundException.NoteNotFoundException;
import com.Notes.NoteRepository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/notes")
public class NoteRestController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteRestController(NoteRepository noteRepo) {
        this.noteRepository = noteRepo;
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException());
    }

    @PostMapping("/new")
    public Note createNote(@RequestBody Note newNote) {
        return noteRepository.save(newNote);
    }

    @PutMapping("/change/{id}")
    public Note editNote(@PathVariable long id, @RequestBody Note newNote) {
        Optional<Note> note = noteRepository.findById(id);

        if (note.isEmpty()) {
            throw new NoteNotFoundException();
        }
        note.get().setNoteData(newNote.getNoteData());
        return noteRepository.save(note.get());
    }
}
