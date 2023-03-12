package com.Notes.NoteRepository;

import com.Notes.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findNoteByIdentifier(String id);

    List<Note> findNotesById(String id);

    Note findNoteByDeleteName(String name);
}
