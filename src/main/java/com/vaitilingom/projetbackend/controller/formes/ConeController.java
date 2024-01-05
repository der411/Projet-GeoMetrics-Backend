package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cone;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.ConeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cones")
@CrossOrigin(origins = "http://localhost:8083")
public class ConeController {

    private final ConeService coneService;
    private final UserService userService;

    public ConeController(ConeService coneService, UserService userService) {
        this.coneService = coneService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Cone> getCones(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return coneService.getConesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Cone getConeById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cone cone = coneService.getConeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cone Invalide:" + id));
        return cone;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Cone addCone(@RequestBody Cone cone, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return coneService.addCone(cone, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Cone updateCone(@PathVariable int id, @RequestBody Cone cone, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cone existingCone = coneService.getConeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cone Invalide:" + id));
        cone.setId(id);
        return coneService.updateCone(cone, user);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCone(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cone existingCone = coneService.getConeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cone Invalide:" + id));
        coneService.deleteCone(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Cone cone, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cone createdCone = coneService.createCone(cone, user);
        return coneService.calculerSurface(createdCone);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/volume")
    public double getPerimetre(@RequestBody Cone cone, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cone createdCone = coneService.createCone(cone, user);
        return coneService.calculerVolume(createdCone);
    }
}
