package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cone;
import com.vaitilingom.projetbackend.models.formes.Sphere;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.SphereService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/spheres")
@CrossOrigin(origins = "http://localhost:8083")
public class SphereController {

    private final SphereService sphereService;
    private final UserService userService;

    public SphereController(SphereService sphereService, UserService userService) {
        this.sphereService = sphereService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Sphere> getSpheres(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return sphereService.getSpheresByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Sphere getSphereById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Sphere sphere = sphereService.getSphereById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Sphere Invalide:" + id));
        return sphere;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Sphere addSphere(@RequestBody Sphere sphere, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return sphereService.addSphere(sphere, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Sphere updateCone(@PathVariable int id, @RequestBody Sphere sphere, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Sphere existingSphere = sphereService.getSphereById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Sphere Invalide:" + id));
        sphere.setId(id);
        return sphereService.updateSphere(sphere, user);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteSphere(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Sphere existingSphere = sphereService.getSphereById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Sphere Invalide:" + id));
        sphereService.deleteSphere(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Sphere sphere) {
        return sphereService.calculerSurface(sphere);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/volume")
    public double getVolume(@RequestBody Sphere sphere) {
        return sphereService.calculerVolume(sphere);
    }
}

