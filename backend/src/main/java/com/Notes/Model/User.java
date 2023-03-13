package com.Notes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.*;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "unique_id")
    private UUID uniqueID;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "USER";

    @Column(name = "salt")
    private String salt = getSecureRandomKey("k1SH4", 64);
    public User() {}
    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password) {
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

    public UUID getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID(UUID id){
        this.uniqueID = id;
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

    @JsonIgnore
    @JsonProperty(value = "salt")
    public String getSalt() {
        return salt;
    }

    private static String getSecureRandomKey(String cipher, int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        SecretKeySpec spec = new SecretKeySpec(secureRandomKeyBytes, cipher);
        return Base64.getEncoder().encodeToString(spec.getEncoded());
    }

}
