package com.vaitilingom.projetbackend.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "ViN10020"; // Remplacez par le mot de passe de votre choix
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Mot de passe haché: " + hashedPassword);
    }
}

