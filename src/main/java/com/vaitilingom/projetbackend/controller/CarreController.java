package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Carre;
import com.vaitilingom.projetbackend.services.CarreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/carres")
public class CarreController {

    private final CarreService carreService;

    public CarreController(CarreService carreService) {
        this.carreService = carreService;
    }

    @GetMapping
    public List<Carre> getCarres() {
        return carreService.getCarres();
    }

    @GetMapping("/{id}")
    public Carre getCarreById(@PathVariable int id) {
        return carreService.getCarreById(id).orElseThrow(() -> new IllegalArgumentException("Invalide Carré ID:" + id));
    }

    @PostMapping
    public Carre addCarre(@RequestBody Carre carre) {
        return carreService.addCarre(carre);
    }

    @PutMapping("/{id}")
    public Carre updateCarre(@PathVariable int id, @RequestBody Carre carre) {
        carre.setId(id);
        return carreService.updateCarre(carre);
    }


    @DeleteMapping("/{id}")
    public void deleteCarre(@PathVariable int id) {
        carreService.deleteCarre(id);
    }
}
