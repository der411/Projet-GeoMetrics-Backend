package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Triangle;
import com.vaitilingom.projetbackend.services.TriangleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/triangles")
public class TriangleController {

    private final TriangleService triangleService;

    public TriangleController(TriangleService triangleService) {
        this.triangleService = triangleService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Triangle> getTriangles() {
        return triangleService.getTriangles();
    }

    @GetMapping("/{id}")
    public Triangle getTriangleById(@PathVariable int id) {
        return triangleService.getTriangleById(id).orElseThrow(() -> new IllegalArgumentException("ID Triangle Invalide:" + id));
    }

    @PostMapping
    public Triangle addTriangle(@RequestBody Triangle triangle) {
        return triangleService.addTriangle(triangle);
    }

    @PutMapping("/{id}")
    public Triangle updateTriangle(@PathVariable int id, @RequestBody Triangle triangle) {
        triangle.setId(id);
        return triangleService.updateTriangle(triangle);
    }

    @DeleteMapping("/{id}")
    public void deleteTriangle(@PathVariable int id) {
        triangleService.deleteTriangle(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Triangle triangle) {
        return triangleService.calculerPerimetre(triangle);
    }

    @PostMapping("/surface")
    public double getSurface(@RequestBody Triangle triangle) {
        return triangleService.calculerSurface(triangle);
    }
}
