package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Cercle;
import com.vaitilingom.projetbackend.repository.CercleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CercleService {
    //Attribut
    private final CercleRepository cercleRepository;

    //Constructeur
    public CercleService(CercleRepository cercleRepository) {
        this.cercleRepository = cercleRepository;
    }

    //Méthodes CRUD

    public List<Cercle> getCercles() {
        return cercleRepository.findAll();
    }

    public Optional<Cercle> getCercleById(int id) {
        return cercleRepository.findById(id);
    }

    public Cercle addCercle(Cercle cercle) {
        return cercleRepository.save(cercle);
    }

    public Cercle updateCercle(Cercle cercle) {
        return cercleRepository.save(cercle);
    }

    public void deleteCercle(int id) {
        cercleRepository.deleteById(id);
    }
}
