package com.Notes.Model;

public class Note {

    private long id;

    private String name;

    private String user;

    private String noteName;

    private String createDate;

    public Note(){}
    public Note(long id, String name, String user, String noteName, String createDate) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.noteName = noteName;
        this.createDate = createDate;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
