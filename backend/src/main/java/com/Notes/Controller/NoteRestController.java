package com.Notes.Controller;

import com.Notes.Model.Note;
import com.Notes.Model.User;
import com.Notes.Model.UserNote;
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

    @GetMapping("/getNote")
    public Note getNote(@RequestBody User user) {
        return noteRepository.findById(user.getUniqueID()).orElseThrow(() -> new NoteNotFoundException());
    }

    @PostMapping("/newNote")
    public Note createNote(@RequestBody UserNote newNote) {
        Note note = newNote.getNote();
        note.setId(newNote.getUser().getUniqueID());

        return noteRepository.save(note);
    }

    @PutMapping("/changeNote")
    public Note editNote(@RequestBody UserNote user) {
        Optional<Note> note = noteRepository.findById(user.getUser().getUniqueID());

        if (note.isEmpty()) {
            throw new NoteNotFoundException();
        }
        note.get().setNoteData(user.getNote().getNoteData());
        return noteRepository.save(note.get());
    }

    @DeleteMapping("/deleteNote")
    public Note deleteNote(@RequestBody User user) {
        Optional<Note> note = noteRepository.findById(user.getUniqueID());

        if (note.isEmpty()) {
            throw new NoteNotFoundException();
        }

        noteRepository.delete(note.get());

        return note.get();
    }
}
