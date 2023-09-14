package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Rectangle;
import com.vaitilingom.projetbackend.services.RectangleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rectangles")
public class RectangleController {

    private final RectangleService rectangleService;

    public RectangleController(RectangleService rectangleService) {
        this.rectangleService = rectangleService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Rectangle> getRectangles() {
        return rectangleService.getRectangles();
    }

    @GetMapping("/{id}")
    public Rectangle getRectangleById(@PathVariable int id) {
        return rectangleService.getRectangleById(id).orElseThrow(() -> new IllegalArgumentException("ID Rectangle Invalide:" + id));
    }

    @PostMapping
    public Rectangle addRectangle(@RequestBody Rectangle rectangle) {
        return rectangleService.addRectangle(rectangle);
    }

    @PutMapping("/{id}")
    public Rectangle updateRectangle(@PathVariable int id, @RequestBody Rectangle rectangle) {
        rectangle.setId(id);
        return rectangleService.updateRectangle(rectangle);
    }
    @DeleteMapping("/{id}")
    public void deleteRectangle(@PathVariable int id) {
        rectangleService.deleteRectangle(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/surface")
    public double getSurface(@RequestBody Rectangle rectangle) {
        return rectangleService.calculerSurface(rectangle);
    }

    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Rectangle rectangle) {
        return rectangleService.calculerPerimetre(rectangle);
    }
}