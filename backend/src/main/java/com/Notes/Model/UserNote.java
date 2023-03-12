package com.Notes.Model;

public class UserNote {

    private User user;
    private Note note;

    public UserNote(User user, Note note) {
        this.user = user;
        this.note = note;
    }

    public User getUser() {
        return user;
    }
    public Note getNote() {
        return note;
    }
}
