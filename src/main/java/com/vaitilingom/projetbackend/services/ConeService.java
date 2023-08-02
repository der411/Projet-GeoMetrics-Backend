package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Cone;
import org.springframework.stereotype.Service;
import com.vaitilingom.projetbackend.repository.ConeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConeService {

    //Attribut
    private final ConeRepository coneRepository;

    //Constructeur
    public ConeService(ConeRepository coneRepository) {
        this.coneRepository = coneRepository;
    }

    //Méthodes CRUD

    public List<Cone> getCones() {
        return coneRepository.findAll();
    }

    public Optional<Cone> getConeById(int id) {
        return coneRepository.findById(id);
    }

    public Cone addCone(Cone cone) {
        return coneRepository.save(cone);
    }

    public Cone updateCone(Cone cone) {
        return coneRepository.save(cone);
    }

    public void deleteCone(int id) {
        coneRepository.deleteById(id);
    }
}
