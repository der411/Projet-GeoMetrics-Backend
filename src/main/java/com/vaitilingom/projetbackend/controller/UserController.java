package com.vaitilingom.projetbackend.controller;


import com.vaitilingom.projetbackend.AuthenticationDTO;
import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.MailParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("inscription");
        this.userService.activation(activation);
    }

    @PostMapping (path = "connexion")
    public Map<String, String> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.mail(), authenticationDTO.password())
        );
        log.info("resultat {}", authenticate.isAuthenticated());
        return null;
    }
}
