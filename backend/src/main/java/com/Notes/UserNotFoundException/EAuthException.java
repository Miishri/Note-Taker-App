package com.Notes.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EAuthException extends RuntimeException{

    public EAuthException (String message) {
        super(message);
    }
}
