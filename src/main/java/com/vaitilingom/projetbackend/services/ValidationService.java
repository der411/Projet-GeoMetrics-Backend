package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.models.Validation;
import com.vaitilingom.projetbackend.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void enregistrer(User user){
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpiration(expiration);
        validation.setActivation(Instant.now());

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);

        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);
    }

    public Validation lireEnFonctionDuCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Code invalide"));
    }
}
