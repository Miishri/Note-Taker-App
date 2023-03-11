package com.Notes.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "unique_id")
    private long uniqueID;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "USER";

    public User() {}

    public User(long id, long uniqueID, String username, String email, String password) {
        this.id = id;
        this.uniqueID = uniqueID;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public long getId() {
        return id;
    }

    public long getUniqueID() {
        return uniqueID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
