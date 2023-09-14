package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Losange;
import com.vaitilingom.projetbackend.services.LosangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/losanges")
public class LosangeController {

    private final LosangeService losangeService;

    public LosangeController(LosangeService losangeService) {
        this.losangeService = losangeService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Losange> getLosanges() {
        return losangeService.getLosanges();
    }

    @GetMapping("/{id}")
    public Losange getLosangeById(@PathVariable int id) {
        return losangeService.getLosangeById(id).orElseThrow(() -> new IllegalArgumentException("ID Losange Invalide:" + id));
    }

    @PostMapping
    public Losange addLosange(@RequestBody Losange losange) {
        return losangeService.addLosange(losange);
    }

    @PutMapping("/{id}")
    public Losange updateLosange(@PathVariable int id, @RequestBody Losange losange) {
        losange.setId(id);
        return losangeService.updateLosange(losange);
    }
    @DeleteMapping("/{id}")
    public void deleteLosange(@PathVariable int id) {
        losangeService.deleteLosange(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Losange losange) {
        return losangeService.calculerPerimetre(losange);
    }

    @PostMapping("/surface")
    public double getSurface(@RequestBody Losange losange) {
        return losangeService.calculerSurface(losange);
    }
}
