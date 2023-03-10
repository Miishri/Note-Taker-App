package com.Notes.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "note_name")
    private String name;

    @Column(name = "creation_date")
    private String createDate;

    @Column(name = "note_data")
    private String noteData;

    public Note(){}
    public Note(long id, String name, String creation, String noteData) {
        this.id = id;
        this.name = name;
        this.createDate = creation;
        this.noteData = noteData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getNoteData() {
        return noteData;
    }
    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

}
