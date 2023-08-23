package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Cercle;
import com.vaitilingom.projetbackend.services.CercleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cercles")
public class CercleController {

    private final CercleService cercleService;

    public CercleController(CercleService cercleService) {
        this.cercleService = cercleService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Cercle> getCercles() {
        return cercleService.getCercles();
    }

    @GetMapping("/{id}")
    public Cercle getCercleById(@PathVariable int id) {
        return cercleService.getCercleById(id).orElseThrow(() -> new IllegalArgumentException("Invalide Cercle ID:" + id));
    }

    @PostMapping
    public Cercle addCercle(@RequestBody Cercle cercle) {
        return cercleService.addCercle(cercle);
    }

    @PutMapping("/{id}")
    public Cercle updateCercle(@PathVariable int id, @RequestBody Cercle cercle) {
        cercle.setId(id);
        return cercleService.updateCercle(cercle);
    }

    @DeleteMapping("/{id}")
    public void deleteCercle(@PathVariable int id) {
        cercleService.deleteCercle(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/surface")
    public double getSurface(@RequestBody Cercle cercle) {
        return cercleService.calculerSurface(cercle);
    }

    @PostMapping("/circonference")
    public double getCirconference(@RequestBody Cercle cercle) {
        return cercleService.calculerCirconference(cercle);
    }
}