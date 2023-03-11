package com.Notes.Model;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Notes")
public class Note {

    @Id
    private long id;
    @Column(name = "note_name")
    private String name;

    @Column(name = "creation_date")
    private String createDate = getCurrentTime();

    @Column(name = "note_data")
    private String noteData;

    public Note(){}
    public Note(User person, String name, String noteData) {
        this.id = person.getId();
        this.name = name;
        this.noteData = noteData;
    }
    private String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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
