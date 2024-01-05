package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Rectangle;
import com.vaitilingom.projetbackend.repository.formes.RectangleRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RectangleService {
    //Attribut
    private final RectangleRepository rectangleRepository;
    private final UserService userService;

    //Constructeur
    public RectangleService(RectangleRepository carreRepository, UserService userService) {
        this.rectangleRepository = carreRepository;
        this.userService = userService;
    }

    public List<Rectangle> getRectanglesByUser(User user) {
        return rectangleRepository.findByUser(user);
    }

    public Optional<Rectangle> getRectangleById(int id, User user) {
        Optional<Rectangle> rectangle = rectangleRepository.findById(id);
        if (rectangle.isPresent() && rectangle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce rectangle");
        }
        return rectangle;
    }

    public Rectangle addRectangle(Rectangle rectangle, User user) {
        rectangle.setUser(user);
        return rectangleRepository.save(rectangle);
    }

    public Rectangle updateRectangle(Rectangle rectangle, User user) {
        rectangle.setUser(user);
        return rectangleRepository.save(rectangle);
    }

    public void deleteRectangle(int id, User user) {
        Optional<Rectangle> rectangle = rectangleRepository.findById(id);
        if (rectangle.isPresent() && rectangle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce rectangle");
        }
        rectangleRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerPerimetre(Rectangle rectangle) {
        return rectangle.perimetre();
    }
    public double calculerSurface(Rectangle rectangle) {
        return rectangle.surface();
    }

    public Rectangle createRectangle(Rectangle rectangle, User user) {
        rectangle.setUser(user);
        Rectangle createdRectangle = rectangleRepository.save(rectangle);
        return createdRectangle;
    }
}
