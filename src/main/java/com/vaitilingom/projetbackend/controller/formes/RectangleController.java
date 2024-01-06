package com.vaitilingom.projetbackend.controller.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Rectangle;
import com.vaitilingom.projetbackend.services.auth.UserService;
import com.vaitilingom.projetbackend.services.formes.RectangleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/rectangles")
@CrossOrigin(origins = "http://localhost:8083")
public class RectangleController {

    private final RectangleService rectangleService;
    private final UserService userService;

    public RectangleController(RectangleService rectangleService, UserService userService) {
        this.rectangleService = rectangleService;
        this.userService = userService;
    }

    //Endpoints CRUD
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINISTRATEUR')")
    @GetMapping
    public List<Rectangle> getRectangles(Principal principal) {
        User user = userService.findByMail(principal.getName());
        return rectangleService.getRectanglesByUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public Rectangle getRectangleById(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Rectangle rectangle = rectangleService.getRectangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Rectangle Invalide:" + id));
        return rectangle;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping
    public Rectangle addRectangle(@RequestBody Rectangle rectangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        return rectangleService.addRectangle(rectangle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PutMapping("/{id}")
    public Rectangle updateRectangle(@PathVariable int id, @RequestBody Rectangle rectangle, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Rectangle existingRectangle = rectangleService.getRectangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Rectangle Invalide:" + id));
        rectangle.setId(id);
        return rectangleService.updateRectangle(rectangle, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/{id}")
    public void deleteRectangle(@PathVariable int id, Principal principal) {
        User user = userService.findByMail(principal.getName());
        Rectangle existingRectangle = rectangleService.getRectangleById(id, user).orElseThrow(() -> new IllegalArgumentException("ID Rectangle Invalide:" + id));
        rectangleService.deleteRectangle(id, user);
    }


    //Endpoints méthodes pragmatiques
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/surface")
    public double getSurface(@RequestBody Rectangle rectangle) {
        return rectangleService.calculerSurface(rectangle);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMINISTRATEUR')")
    @PostMapping("/perimetre")
    public double getPerimetre(@RequestBody Rectangle rectangle) {
        return rectangleService.calculerPerimetre(rectangle);
    }
}