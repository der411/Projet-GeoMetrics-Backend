package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.TriangleScalene;
import com.vaitilingom.projetbackend.repository.TriangleScaleneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TriangleScaleneService {
    //Attribut
    private final TriangleScaleneRepository triangleScaleneRepository;

    //Constructeur
    public TriangleScaleneService(TriangleScaleneRepository triangleScaleneRepository) {
        this.triangleScaleneRepository = triangleScaleneRepository;
    }

    //Méthodes CRUD

    public List<TriangleScalene> getTriangleScalenes() {
        return triangleScaleneRepository.findAll();
    }

    public Optional<TriangleScalene> getTriangleScaleneById(int id) {
        return triangleScaleneRepository.findById(id);
    }

    public TriangleScalene addTriangleScalene(TriangleScalene triangleScalene) {
        return triangleScaleneRepository.save(triangleScalene);
    }

    public TriangleScalene updateTriangleScalene(TriangleScalene triangleScalene) {
        return triangleScaleneRepository.save(triangleScalene);
    }

    public void deleteTriangleScalene(int id) {
        triangleScaleneRepository.deleteById(id);
    }
}
