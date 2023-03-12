package com.Notes.Model;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "note_name")
    private String name;

    @Column(name = "creation_date")
    private String createDate = getCurrentTime();

    @Column(name = "note_data")
    private String noteData;

    @Column(name = "delete_name")
    private String deleteName;

    public Note(){}
    public Note(String name, String noteData) {
        this.name = name;
        this.noteData = noteData;
    }
    private String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDeleteName() {
        return deleteName;
    }
    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID id) {
        this.identifier = id.toString();
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
