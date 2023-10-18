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

    @PostMapping (path = "/validation")
    public void validation(@RequestBody Map<String, String> validation){
        log.info("validation");
        this.userService.activation(validation);
    }

    @PostMapping(path = "/connexion")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.mail(), authenticationDTO.passWord())
            );

            // Récupérer l'utilisateur et son rôle
            User user = (User) authenticate.getPrincipal();
            String role = user.getRole().getLibelle().toString(); // Obtenir directement le libellé du rôle

            // Générer le JWT pour l'utilisateur authentifié
            String jwt = Jwts.builder()
                    .setSubject(authenticationDTO.mail())
                    .claim("role", role) // Ajouter le rôle comme une claim
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // Expiration après 10 jours par exemple
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
    public ResponseEntity<String> setAdminRole(@PathVariable String mail) {
        try {
            userService.setAdminRoleToUser(mail);
            return new ResponseEntity<>("User role set to ADMINISTRATEUR successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
