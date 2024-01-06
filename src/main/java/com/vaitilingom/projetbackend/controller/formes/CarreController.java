package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.CarreService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/carres")
@CrossOrigin(origins = "http://localhost:8083")
public class CarreController {

    private final CarreService carreService;
    private final UserService userService;

    public CarreController(CarreService carreService, UserService userService) {
        this.carreService = carreService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Carre> getCarres(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return carreService.getCarresByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Carre getCarreById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Carre carre = carreService.getCarreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Carré Invalide:" + id));
        return carre;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Carre addCarre(@RequestBody Carre carre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return carreService.addCarre(carre, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Carre updateCarre(@PathVariable int id, @RequestBody Carre carre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Carre existingCarre = carreService.getCarreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Carré Invalide:" + id));
        carre.setId(id);
        return carreService.updateCarre(carre, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCarre(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Carre existingCarre = carreService.getCarreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Carré Invalide:" + id));
        carreService.deleteCarre(id, user);
    }


    //Endpoints méthodes pragmatiques
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Carre carre) {
        return carreService.calculerSurface(carre);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Carre carre) {
        return carreService.calculerPerimetre(carre);
    }
}


