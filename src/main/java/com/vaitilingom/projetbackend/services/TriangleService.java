package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Triangle;
import com.vaitilingom.projetbackend.repository.TriangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TriangleService {
    //Attribut
    private final TriangleRepository triangleRepository;

    //Constructeur
    public TriangleService(TriangleRepository triangleRepository) {
        this.triangleRepository = triangleRepository;
    }

    //Méthodes CRUD

    public List<Triangle> getTriangles() {
        return triangleRepository.findAll();
    }

    public Optional<Triangle> getTriangleById(int id) {
        return triangleRepository.findById(id);
    }

    public Triangle addTriangle(Triangle triangle) {
        return triangleRepository.save(triangle);
    }

    public Triangle updateTriangle(Triangle triangle) {
        return triangleRepository.save(triangle);
    }

    public void deleteTriangle(int id) {
        triangleRepository.deleteById(id);
    }
}
