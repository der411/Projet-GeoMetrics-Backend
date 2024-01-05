package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cercle;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.CercleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cercles")
@CrossOrigin(origins = "http://localhost:8083")
public class CercleController {

    private final CercleService cercleService;

    private final UserService userService;

    public CercleController(CercleService cercleService, UserService userService) {
        this.cercleService = cercleService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Cercle> getCercles(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cercleService.getCerclesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Cercle getCercleById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cercle cercle = cercleService.getCercleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cercle Invalide:" + id));
        return cercle;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Cercle addCercle(@RequestBody Cercle cercle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cercleService.addCercle(cercle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Cercle updateCercle(@PathVariable int id, @RequestBody Cercle cercle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cercle existingCercle = cercleService.getCercleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cercle Invalide:" + id));
        cercle.setId(id);
        return cercleService.updateCercle(cercle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCercle(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cercle existingCercle = cercleService.getCercleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cercle Invalide:" + id));
        cercleService.deleteCercle(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Cercle cercle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cercle createdCercle = cercleService.createCercle(cercle, user);
        return cercleService.calculerSurface(createdCercle);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/circonference")
    public double getCirconference(@RequestBody Cercle cercle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cercle createdCercle = cercleService.createCercle(cercle, user);
        return cercleService.calculerCirconference(createdCercle);
    }
}