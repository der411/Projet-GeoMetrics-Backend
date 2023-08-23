package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Cylindre;
import com.vaitilingom.projetbackend.services.CylindreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cylindres")
public class CylindreController {

    private final CylindreService cylindreService;

    public CylindreController(CylindreService cylindreService) {
        this.cylindreService = cylindreService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Cylindre> getCylindres() {
        return cylindreService.getCylindres();
    }

    @GetMapping("/{id}")
    public Cylindre getCylindreById(@PathVariable int id) {
        return cylindreService.getCylindreById(id).orElseThrow(() -> new IllegalArgumentException("Invalide Cylindre ID:" + id));
    }

    @PostMapping
    public Cylindre addCylindre(@RequestBody Cylindre cylindre) {
        return cylindreService.addCylindre(cylindre);
    }

    @PutMapping("/{id}")
    public Cylindre updateCylindre(@PathVariable int id, @RequestBody Cylindre cylindre) {
        cylindre.setId(id);
        return cylindreService.updateCylindre(cylindre);
    }

    @DeleteMapping("/{id}")
    public void deleteCylindre(@PathVariable int id) {
        cylindreService.deleteCylindre(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/surface")
    public double getSurface(@RequestBody Cylindre cylindre) {
        return cylindreService.calculerSurface(cylindre);
    }

    @PostMapping("/volume")
    public double getVolume(@RequestBody Cylindre cylindre) {
        return cylindreService.calculerVolume(cylindre);
    }
}
