package com.Notes.Controller;

import com.Notes.Model.User;
import com.Notes.NoteNotFoundException.NoteNotFoundException;
import com.Notes.NoteRepository.UserRepository;
import com.Notes.UserNotFoundException.UserFoundException;
import com.Notes.UserNotFoundException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {
    private final String salt = "#/<.;9%$@";
    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getUser")
    public User getUserByEmail(@RequestBody String username) {
        User userCheck = userRepository.findUserByUsername(username);
        userCheck.setPassword("Hidden");
        return userCheck;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        User optionalUser;

        optionalUser = userRepository.findUserByUsername(user.getUsername());

        if (optionalUser != null) {
            throw new UserNotFoundException();
        }

        optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (optionalUser != null) {
            throw new UserNotFoundException();
        }

        optionalUser = user;

        UUID uuid = UUID.randomUUID();

        optionalUser.setUniqueID(uuid);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(salt + optionalUser.getPassword());
        optionalUser.setPassword(encodedPassword);

        return userRepository.save(optionalUser);
    }

    @PostMapping("/changeUsername")
    public User editUserPassword(@RequestBody User user) {
        User usernameCheck = userRepository.findUserByUsername(user.getUsername());
        if (usernameCheck != null) {
            throw new UserNotFoundException();
        }

        User checkUser = userRepository.findUserByEmail(user.getEmail());
        checkUser.setUsername(user.getUsername());

        return userRepository.save(checkUser);
    }

    @PostMapping("/changeEmail")
    public User editUserEmail(@RequestBody User user) {
        User usernameCheck = userRepository.findUserByUsername(user.getUsername());
        usernameCheck.setEmail(user.getEmail());

        User emailCheck = userRepository.findUserByEmail(usernameCheck.getEmail());
        if (emailCheck != null) {
            throw new UserNotFoundException();
        }

        return userRepository.save(usernameCheck);
    }

    @PostMapping("/changePassword")
    public User editUser(@RequestBody User user) {
        User checkUser = userRepository.findUserByEmail(user.getEmail());

        if (checkUser == null) {
            throw new UserNotFoundException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(salt + user.getPassword());
        checkUser.setPassword(encodedPassword);

        return userRepository.save(checkUser);
    }

    @DeleteMapping("/deleteUser")
    public User deleteUser(@RequestBody User user) {
        Optional<User> userCheck = Optional.ofNullable(userRepository.findUserByEmail(user.getEmail()));

        if (userCheck.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(userCheck.get());

        return userCheck.get();
    }

}
