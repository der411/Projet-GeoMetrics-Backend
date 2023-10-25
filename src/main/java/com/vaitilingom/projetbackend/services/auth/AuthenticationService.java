package com.vaitilingom.projetbackend.services.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.security.Key;
@Getter
@Service
public class AuthenticationService {

    private final Key jwtSecretKey;

    public AuthenticationService() {
        this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

}
