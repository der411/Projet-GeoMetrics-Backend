package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Cylindre;
import com.vaitilingom.projetbackend.repository.CylindreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CylindreService {

    //Attribut
    private final CylindreRepository cylindreRepository;

    //Constructeur
    public CylindreService(CylindreRepository cylindreRepository) {
        this.cylindreRepository = cylindreRepository;
    }

    //Méthodes CRUD

    public List<Cylindre> getCylindres() {
        return cylindreRepository.findAll();
    }

    public Optional<Cylindre> getCylindreById(int id) {
        return cylindreRepository.findById(id);
    }

    public Cylindre addCylindre(Cylindre cylindre) {
        return cylindreRepository.save(cylindre);
    }

    public Cylindre updateCylindre(Cylindre cylindre) {
        return cylindreRepository.save(cylindre);
    }

    public void deleteCylindre(int id) {
        cylindreRepository.deleteById(id);
    }
}
