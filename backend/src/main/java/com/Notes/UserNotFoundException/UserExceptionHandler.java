package com.Notes.UserNotFoundException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public UserNotFound exceptionHandler(UserNotFoundException userNotFoundException) {
        UserNotFound noteNotFound = new UserNotFound();
        noteNotFound.setMessage("Following user was not found or exists already");
        return noteNotFound;
    }

}
