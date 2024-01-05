package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cube;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.CubeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cubes")
@CrossOrigin(origins = "http://localhost:8083")
public class CubeController {

    private final CubeService cubeService;
    private final UserService userService;

    public CubeController(CubeService cubeService, UserService userService) {
        this.cubeService = cubeService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Cube> getCubes(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cubeService.getCubesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Cube getCubeById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cube cube = cubeService.getCubeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cube Invalide:" + id));
        return cube;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Cube addCube(@RequestBody Cube cube, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return cubeService.addCube(cube, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Cube updateCube(@PathVariable int id, @RequestBody Cube cube, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cube existingCube = cubeService.getCubeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cube Invalide:" + id));
        cube.setId(id);
        return cubeService.updateCube(cube, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteCube(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cube existingCube = cubeService.getCubeById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Cube Invalide:" + id));
        cubeService.deleteCube(id, user);
    }

    //Endpoints méthodes pragmatiques

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Cube cube, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cube createdCube = cubeService.createCube(cube, user);
        return cubeService.calculerSurface(createdCube);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/volume")
    public double getPerimetre(@RequestBody Cube cube, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Cube createdCube = cubeService.createCube(cube, user);
        return cubeService.calculerVolume(createdCube);
    }
}
