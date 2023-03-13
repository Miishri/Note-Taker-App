package com.Notes.Service;

import com.Notes.Model.User;
import com.Notes.NoteRepository.UserRepository;
import com.Notes.UserNotFoundException.EAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.synth.SynthTextAreaUI;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EAuthException {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserByEmail(email.toLowerCase());

        if (user == null) {
            throw new EAuthException("User was not found");
        }
        String encodedPassword = encoder.encode(password + user.getSalt());

        if (!encoder.matches(password + user.getSalt(), encodedPassword)) {
            throw new EAuthException("Password does not match for the user : " + user.getUsername());
        }

        return user;
    }
}