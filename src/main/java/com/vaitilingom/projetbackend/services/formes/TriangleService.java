package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Triangle;
import com.vaitilingom.projetbackend.repository.formes.TriangleRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TriangleService {
    //Attribut
    private final TriangleRepository triangleRepository;
    private final UserService userService;

    //Constructeur
    public TriangleService(TriangleRepository triangleRepository, UserService userService) {
        this.triangleRepository = triangleRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Triangle> getTrianglesByUser(User user) {
        return triangleRepository.findByUser(user);
    }

    public Optional<Triangle> getTriangleById(int id, User user) {
        Optional<Triangle> triangle = triangleRepository.findById(id);
        if (triangle.isPresent() && triangle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce triangle");
        }
        return triangle;
    }

    public Triangle addTriangle(Triangle triangle, User user) {
        triangle.setUser(user);
        return triangleRepository.save(triangle);
    }

    public Triangle updateTriangle(Triangle triangle, User user) {
        triangle.setUser(user);
        return triangleRepository.save(triangle);
    }

    public void deleteTriangle(int id, User user) {
        Optional<Triangle> triangle = triangleRepository.findById(id);
        if (triangle.isPresent() && triangle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce triangle");
        }
        triangleRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Triangle triangle) {return triangle.surface();}

    public double calculerPerimetre(Triangle triangle) {
        return triangle.perimetre();
    }

}
