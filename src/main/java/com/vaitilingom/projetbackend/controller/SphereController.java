package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Sphere;
import com.vaitilingom.projetbackend.services.SphereService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/spheres")
public class SphereController {

    private final SphereService sphereService;

    public SphereController(SphereService sphereService) {
        this.sphereService = sphereService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Sphere> getSpheres() {
        return sphereService.getSpheres();
    }

    @GetMapping("/{id}")
    public Sphere getSphereById(@PathVariable int id) {
        return sphereService.getSphereById(id).orElseThrow(() -> new IllegalArgumentException("ID Sphère Invalide:" + id));
    }

    @PostMapping
    public Sphere addSphere(@RequestBody Sphere sphere) {
        return sphereService.addSphere(sphere);
    }

    @PutMapping("/{id}")
    public Sphere updateSphere(@PathVariable int id, @RequestBody Sphere sphere) {
        sphere.setId(id);
        return sphereService.updateSphere(sphere);
    }
    @DeleteMapping("/{id}")
    public void deleteSphere(@PathVariable int id) {
        sphereService.deleteSphere(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/surface")
    public double getSurface(@RequestBody Sphere sphere) {
        return sphereService.calculerSurface(sphere);
    }

    @PostMapping("/volume")
    public double getVolume(@RequestBody Sphere sphere) {
        return sphereService.calculerVolume(sphere);
    }
}

