package com.Notes.NoteRepository;

import com.Notes.Model.User;
import com.Notes.UserNotFoundException.EAuthException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
    User findUserByEmailAndUsername(String email, String username);
    User findUserByEmailAndPassword(String email, String password) throws EAuthException;
}
