package com.vaitilingom.projetbackend.controller;


import com.vaitilingom.projetbackend.models.Cone;
import com.vaitilingom.projetbackend.services.ConeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cones")
public class ConeController {

    private final ConeService coneService;

    public ConeController(ConeService coneService) {
        this.coneService = coneService;
    }

    @GetMapping
    public List<Cone> getCones() {
        return coneService.getCones();
    }

    @GetMapping("/{id}")
    public Cone getConeById(@PathVariable int id) {
        return coneService.getConeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Cone ID:" + id));
    }

    @PostMapping
    public Cone addCone(@RequestBody Cone cone) {
        return coneService.addCone(cone);
    }

    @PutMapping("/{id}")
    public Cone updateCone(@PathVariable int id, @RequestBody Cone cone) {
        cone.setId(id);
        return coneService.updateCone(cone);
    }


    @DeleteMapping("/{id}")
    public void deleteCone(@PathVariable int id) {
        coneService.deleteCone(id);
    }
}
