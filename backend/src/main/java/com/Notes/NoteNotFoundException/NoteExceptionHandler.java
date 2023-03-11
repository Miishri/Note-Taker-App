package com.Notes.NoteNotFoundException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoteExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public NoteNotFound exceptionHandler(NoteNotFoundException noteException) {
        NoteNotFound noteNotFound = new NoteNotFound();
        noteNotFound.setMessage("Following note was not found");
        return noteNotFound;
    }
}
