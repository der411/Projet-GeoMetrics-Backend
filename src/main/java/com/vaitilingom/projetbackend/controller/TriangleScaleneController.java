package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.TriangleScalene;
import com.vaitilingom.projetbackend.services.TriangleScaleneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/triangleScalenes")
public class TriangleScaleneController {

    private final TriangleScaleneService triangleScaleneService;

    public TriangleScaleneController(TriangleScaleneService triangleScaleneService) {
        this.triangleScaleneService = triangleScaleneService;
    }

    @GetMapping
    public List<TriangleScalene> getTriangleScalenes() {
        return triangleScaleneService.getTriangleScalenes();
    }

    @GetMapping("/{id}")
    public TriangleScalene getTriangleScaleneById(@PathVariable int id) {
        return triangleScaleneService.getTriangleScaleneById(id).orElseThrow(() -> new IllegalArgumentException("Invalid TriangleScalene ID:" + id));
    }

    @PostMapping
    public TriangleScalene addTriangleScalene(@RequestBody TriangleScalene triangleScalene) {
        return triangleScaleneService.addTriangleScalene(triangleScalene);
    }

    @PutMapping("/{id}")
    public TriangleScalene updateTriangleScalene(@PathVariable int id, @RequestBody TriangleScalene triangleScalene) {
        triangleScalene.setId(id);
        return triangleScaleneService.updateTriangleScalene(triangleScalene);
    }


    @DeleteMapping("/{id}")
    public void deleteTriangleScalene(@PathVariable int id) {
        triangleScaleneService.deleteTriangleScalene(id);
    }
}
