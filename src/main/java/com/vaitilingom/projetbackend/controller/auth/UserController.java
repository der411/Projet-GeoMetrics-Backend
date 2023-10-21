package com.vaitilingom.projetbackend.controller.auth;


import com.vaitilingom.projetbackend.AuthenticationDTO;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.services.auth.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:8083")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private final String JWT_SECRET;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, @Qualifier("jwtSecret") String JWT_SECRET) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.JWT_SECRET = JWT_SECRET;
    }

    @PostMapping (path = "/inscription")
    public void inscription(@RequestBody User user){
        log.info("inscription");
        this.userService.inscription(user);
    }

    @PostMapping(path = "/validation")
    public ResponseEntity<Map<String, Object>> validation(@RequestBody Map<String, String> validation) {
        try {
            log.info("validation");
            boolean isValid = this.userService.activation(validation);

            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/connexion")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.mail(), authenticationDTO.passWord())
            );

            User user = (User) authenticate.getPrincipal();

            // Récupération de tous les rôles
            String roles = user.getRoles().stream()
                    .map(role -> role.getLibelle().toString())
                    .collect(Collectors.joining(","));

            // Générer le JWT pour l'utilisateur authentifié
            String jwt = Jwts.builder()
                    .setSubject(authenticationDTO.mail())
                    .claim("roles", roles) // Ajouter tous les rôles comme une claim
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                    .compact();

            response.put("message", "Connexion réussie");
            response.put("status", "success");
            response.put("token", jwt);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Erreur lors de la connexion : identifiant ou mot de passe incorrect.");
            response.put("status", "failure");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATEUR')")
    @PostMapping(path = "/set-admin/{mail}")
    public ResponseEntity<Map<String, Object>> setAdminRole(@PathVariable String mail) {
        try {
            userService.setAdminRoleToUser(mail);

            Map<String, Object> response = new HashMap<>();
            response.put("token", "YOUR_TOKEN_HERE"); // Remplacez par la logique de génération de votre token si nécessaire
            response.put("user", Map.of("role", "ADMINISTRATEUR")); // Remplacez par la logique d'obtention du rôle si nécessaire

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
