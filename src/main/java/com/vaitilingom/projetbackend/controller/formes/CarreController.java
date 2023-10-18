package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.services.formes.CarreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;


@RestController
@RequestMapping("/api/carres")
@CrossOrigin(origins = "http://localhost:8083")
public class CarreController {

    private final CarreService carreService;

    public CarreController(CarreService carreService) {
        this.carreService = carreService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Carre> getCarres() {
        return carreService.getCarres();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Carre getCarreById(@PathVariable int id) {
        return carreService.getCarreById(id).orElseThrow(() -> new IllegalArgumentException("ID Carré Invalide:" + id));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Carre addCarre(@RequestBody Carre carre) {
        return carreService.addCarre(carre);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Carre updateCarre(@PathVariable int id, @RequestBody Carre carre) {
        carre.setId(id);
        return carreService.updateCarre(carre);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCarre(@PathVariable int id) {
        carreService.deleteCarre(id);
    }


    //Endpoints méthodes pragmatiques
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Carre carre) {
        return carreService.calculerSurface(carre);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Carre carre) {
        return carreService.calculerPerimetre(carre);
    }
}


