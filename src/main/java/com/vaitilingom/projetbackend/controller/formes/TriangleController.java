package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Triangle;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.TriangleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/triangles")
@CrossOrigin(origins = "http://localhost:8083")
public class TriangleController {

    private final TriangleService triangleService;
    private final UserService userService;

    public TriangleController(TriangleService triangleService, UserService userService) {
        this.triangleService = triangleService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Triangle> getTriangles(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return triangleService.getTrianglesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Triangle getTriangleById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Triangle triangle = triangleService.getTriangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Triangle Invalide:" + id));
        return triangle;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Triangle addTriangle(@RequestBody Triangle triangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return triangleService.addTriangle(triangle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Triangle updateTriangle(@PathVariable int id, @RequestBody Triangle triangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Triangle existingTriangle = triangleService.getTriangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Triangle Invalide:" + id));
        triangle.setId(id);
        return triangleService.updateTriangle(triangle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteTriangle(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Triangle existingTriangle = triangleService.getTriangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Triangle Invalide:" + id));
        triangleService.deleteTriangle(id, user);
    }


    //Endpoints méthodes pragmatiques
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Triangle triangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Triangle createdTriangle = triangleService.createTriangle(triangle, user);
        return triangleService.calculerSurface(createdTriangle);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_USER')")
    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Triangle triangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Triangle createdTriangle = triangleService.createTriangle(triangle, user);
        return triangleService.calculerPerimetre(createdTriangle);
    }
}
