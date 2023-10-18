package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.formes.Triangle;
import com.vaitilingom.projetbackend.services.formes.TriangleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/triangles")
@CrossOrigin(origins = "http://localhost:8083")
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
