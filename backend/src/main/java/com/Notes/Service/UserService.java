package com.Notes.Service;

import com.Notes.Model.User;
import com.Notes.UserNotFoundException.EAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EAuthException;

}
