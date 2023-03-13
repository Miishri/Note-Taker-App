package com.Notes.Controller;

import com.Notes.Model.User;
import com.Notes.NoteRepository.UserRepository;
import com.Notes.Service.UserDetailsServiceImpl;
import com.Notes.Service.UserService;
import com.Notes.UserNotFoundException.EAuthException;
import com.Notes.UserNotFoundException.UserFoundException;
import com.Notes.UserNotFoundException.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.Notes.Service.Constants;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {
    private UserRepository userRepository;
    private UserDetailsServiceImpl userService;
    @Autowired
    public UserRestController(UserRepository userRepository, UserDetailsServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User getUserByEmail(@RequestBody String email) {
        User userCheck = userRepository.findUserByEmail(email.toLowerCase());
        userCheck.setPassword("Hidden");
        return userCheck;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        User optionalUser = userRepository.findUserByEmailAndUsername(user.getEmail(), user.getUsername());

        if (optionalUser != null) {
            throw new UserNotFoundException();
        }

        optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser != null) {
            throw new UserNotFoundException();
        }
        optionalUser = userRepository.findUserByUsername(user.getUsername());
        if (optionalUser != null) {
            throw new UserNotFoundException();
        }

        optionalUser = user;
        optionalUser.setEmail(user.getEmail().toLowerCase());
        UUID uuid = UUID.randomUUID();

        optionalUser.setUniqueID(uuid);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(optionalUser.getPassword() + user.getSalt());
        optionalUser.setPassword(encodedPassword);

        return userRepository.save(optionalUser);
    }

    @GetMapping ("/login")
    public User userLogin(@RequestBody User userCheck) throws EAuthException{
        User user = userService.validateUser(userCheck.getEmail(), userCheck.getPassword());

        //token for future
        String token = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .setSubject(user.getUsername())
                .setId(user.getUniqueID().toString())
                .setIssuedAt(java.sql.Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
                .compact();

        return user;

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
        String encodedPassword = encoder.encode(user.getSalt() + user.getPassword());
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
