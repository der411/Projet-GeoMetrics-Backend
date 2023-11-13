package com.vaitilingom.projetbackend.services.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
@Getter
@Service
public class AuthenticationService {

    private Key jwtSecretKey;

    public AuthenticationService(@Value("${jwt.secret}") String secret) {
        System.out.println("Secret value: " + secret); // Ajoutez cette ligne
        byte[] decodedKey = java.util.Base64.getDecoder().decode(secret);
        this.jwtSecretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public Key getJwtSecretKey() {

        return jwtSecretKey;
    }

}
