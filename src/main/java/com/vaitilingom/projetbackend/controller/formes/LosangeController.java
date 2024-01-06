package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Losange;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.LosangeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/losanges")
@CrossOrigin(origins = "http://localhost:8083")
public class LosangeController {

    private final LosangeService losangeService;
    private final UserService userService;

    public LosangeController(LosangeService losangeService, UserService userService) {
        this.losangeService = losangeService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Losange> getLosanges(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return losangeService.getLosangesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Losange getLosangeById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Losange losange = losangeService.getLosangeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Losange Invalide:" + id));
        return losange;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Losange addLosange(@RequestBody Losange losange, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return losangeService.addLosange(losange, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Losange updateLosange(@PathVariable int id, @RequestBody Losange losange, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Losange existingLosange = losangeService.getLosangeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Losange Invalide:" + id));
        losange.setId(id);
        return losangeService.updateLosange(losange, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteLosange(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Losange existingLosange = losangeService.getLosangeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Losange Invalide:" + id));
        losangeService.deleteLosange(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Losange losange) {
        return losangeService.calculerSurface(losange);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Losange losange) {
        return losangeService.calculerPerimetre(losange);
    }
}
