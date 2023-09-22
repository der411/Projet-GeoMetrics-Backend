package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void envoyer(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@géométrics.com");
        message.setTo(validation.getUser().getMail());
        message.setSubject("Votre code d'activation");
        String texte = String.format("Bonjour %s, \n\nVotre code d'activation est %s. \n\nÀ très bientôt sur Géo Métrics !",
                validation.getUser().getNom(),
                validation.getCode()
        );
        message.setText(texte);

        javaMailSender.send(message);
    }
}
