package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Cube;
import com.vaitilingom.projetbackend.services.CubeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cubes")
public class CubeController {

    private final CubeService cubeService;

    public CubeController(CubeService cubeService) {
        this.cubeService = cubeService;
    }

    //Endpoints CRUD
    @GetMapping
    public List<Cube> getCubes() {
        return cubeService.getCubes();
    }

    @GetMapping("/{id}")
    public Cube getCubeById(@PathVariable int id) {
        return cubeService.getCubeById(id).orElseThrow(() -> new IllegalArgumentException("ID Cube Invalide:" + id));
    }

    @PostMapping
    public Cube addCube(@RequestBody Cube cube) {
        return cubeService.addCube(cube);
    }

    @PutMapping("/{id}")
    public Cube updateCube(@PathVariable int id, @RequestBody Cube cube) {
        cube.setId(id);
        return cubeService.updateCube(cube);
    }
    @DeleteMapping("/{id}")
    public void deleteCube(@PathVariable int id) {
        cubeService.deleteCube(id);
    }

    //Endpoints méthodes pragmatiques

    @PostMapping("/surface")
    public double getSurface(@RequestBody Cube cube) {
        return cubeService.calculerSurface(cube);
    }

    @PostMapping("/volume")
    public double getPerimetre(@RequestBody Cube cube) {
        return cubeService.calculerVolume(cube);
    }
}
