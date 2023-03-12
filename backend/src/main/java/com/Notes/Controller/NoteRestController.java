package com.Notes.Controller;

import com.Notes.Model.Note;
import com.Notes.Model.User;
import com.Notes.Model.UserNote;
import com.Notes.NoteNotFoundException.NoteNotFoundException;
import com.Notes.NoteRepository.NoteRepository;
import com.Notes.NoteRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/notes")
public class NoteRestController {

    private NoteRepository noteRepository;

    private UserRepository userRepository;

    @Autowired
    public NoteRestController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/getNote")
    public List<Note> getNotes(@RequestBody User user) {

        Optional<User> userCheck = Optional.ofNullable(userRepository.findUserByEmail(user.getEmail()));

        if (userCheck.isEmpty()) {
            throw new NoteNotFoundException();
        }

        return noteRepository.findNotesById(userCheck.get().getUniqueID().toString());
    }

    @PostMapping("/newNote")
    public Note createNote(@RequestBody UserNote newNote) {
        Optional<User> userCheck = Optional.ofNullable(userRepository.findUserByEmail(newNote.getUser().getEmail()));
        Note note = newNote.getNote();

        if (note.getNoteData().isEmpty()) {
            note.setNoteData("Empty note");
        }

        if (note.getName().isEmpty()) {
            note.setName("Note");
        }

        note.setIdentifier(userCheck.get().getUniqueID());
        note.setDeleteName(note.getName() + note.getId());

        return noteRepository.save(note);
    }

    @PutMapping("/changeNote")
    public Note editNote(@RequestBody UserNote user) {
        Optional<User> userCheck = Optional.ofNullable(userRepository.findUserByEmail(user.getUser().getEmail()));

        if (userCheck.isEmpty()) {
            throw new NoteNotFoundException();
        }

        Note newNote = user.getNote();
        newNote.setNoteData(user.getNote().getNoteData());

        if (newNote.getNoteData().isEmpty()) {
            newNote.setNoteData("Empty note");
        }

        if (newNote.getName().isEmpty()) {
            newNote.setName("Note");
        }

        return noteRepository.save(newNote);
    }

    @DeleteMapping("/deleteNote")
    public Note deleteNote(@RequestBody UserNote user) {
        User note = userRepository.findUserByEmail(user.getUser().getEmail());

        if (note == null) {
            throw new NoteNotFoundException();
        }

        Note newNote = noteRepository.findNoteByIdentifier(user.getNote().getIdentifier());

        noteRepository.delete(newNote);

        return newNote;
    }
}
