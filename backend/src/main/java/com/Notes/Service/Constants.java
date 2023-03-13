package com.Notes.Service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constants {
    public static final String key = "usersecretkeyforauto";

    public static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;
}
