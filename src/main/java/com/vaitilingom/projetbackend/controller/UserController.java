package com.vaitilingom.projetbackend.controller;


import com.vaitilingom.projetbackend.AuthenticationDTO;
import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping (path = "inscription")
    public void inscription(@RequestBody User user){
        log.info("inscription");
        this.userService.inscription(user);
    }

    @PostMapping (path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        log.info("activation");
        this.userService.activation(activation);
    }

    @PostMapping(path = "connexion")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.mail(), authenticationDTO.password())
            );

            // L'utilisateur est authentifié avec succès, vous pouvez générer un token ici si nécessaire
            response.put("message", "Connexion réussie");
            response.put("status", "success");
            // Si vous générez un JWT ou un autre token, ajoutez-le à la réponse
            // response.put("token", "YOUR_GENERATED_TOKEN");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // L'authentification a échoué
            response.put("message", "Erreur lors de la connexion : identifiant ou mot de passe incorrect.");
            response.put("status", "failure");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
