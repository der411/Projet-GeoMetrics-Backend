package com.vaitilingom.projetbackend.services.auth;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.auth.Validation;
import com.vaitilingom.projetbackend.repository.auth.ValidationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Slf4j
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
        log.info("Code reçu pour validation: " + code);
        Validation validation = this.validationRepository.findByCode(code)
                .orElseThrow(() -> {
                    log.error("Aucune validation trouvée pour le code: " + code);
                    return new RuntimeException("Code invalide");
                });
        log.info("Validation trouvée pour le code: " + validation);
        return validation;
    }

}
