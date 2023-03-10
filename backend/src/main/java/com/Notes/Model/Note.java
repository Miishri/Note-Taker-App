package com.Notes.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public Note(long id, String name, String noteData, String createDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        this.id = id;
        this.name = name;
        this.createDate = dtf.format(now);
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
