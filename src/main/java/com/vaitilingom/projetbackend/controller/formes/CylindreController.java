package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cylindre;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.CylindreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cylindres")
@CrossOrigin(origins = "http://localhost:8083")
public class CylindreController {

    private final CylindreService cylindreService;
    private final UserService userService;

    public CylindreController(CylindreService cylindreService, UserService userService) {
        this.cylindreService = cylindreService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Cylindre> getCylindres(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cylindreService.getCylindresByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Cylindre getCylindreById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cylindre cylindre = cylindreService.getCylindreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cylindre Invalide:" + id));
        return cylindre;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Cylindre addCylindre(@RequestBody Cylindre cylindre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cylindreService.addCylindre(cylindre, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Cylindre updateCylindre(@PathVariable int id, @RequestBody Cylindre cylindre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cylindre existingCylindre = cylindreService.getCylindreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cylindre Invalide:" + id));
        cylindre.setId(id);
        return cylindreService.updateCylindre(cylindre, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCylindre(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cylindre existingCylindre = cylindreService.getCylindreById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cylindre Invalide:" + id));
        cylindreService.deleteCylindre(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Cylindre cylindre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cylindre createdCylindre = cylindreService.createCylindre(cylindre, user);
        return cylindreService.calculerSurface(createdCylindre);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/volume")
    public double getPerimetre(@RequestBody Cylindre cylindre, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cylindre createdCylindre = cylindreService.createCylindre(cylindre, user);
        return cylindreService.calculerVolume(createdCylindre);
    }
}
