package com.vaitilingom.projetbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class}) ---> pour desactiver la sécurité
public class ProjetBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetBackendApplication.class, args);
    }

}

