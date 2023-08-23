package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Rectangle;
import com.vaitilingom.projetbackend.repository.RectangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RectangleService {
    //Attribut
    private final RectangleRepository rectangleRepository;

    //Constructeur
    public RectangleService(RectangleRepository carreRepository) {
        this.rectangleRepository = carreRepository;
    }

    public List<Rectangle> getRectangles() {
        return rectangleRepository.findAll();
    }

    public Optional<Rectangle> getRectangleById(int id) {
        return rectangleRepository.findById(id);
    }

    public Rectangle addRectangle(Rectangle rectangle) {
        return rectangleRepository.save(rectangle);
    }

    public Rectangle updateRectangle(Rectangle rectangle) {
        return rectangleRepository.save(rectangle);
    }

    public void deleteRectangle(int id) {
        rectangleRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerPerimetre(Rectangle rectangle) {
        return rectangle.perimetre();
    }
    public double calculerSurface(Rectangle rectangle) {
        return rectangle.surface();
    }
}
