package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.formes.Cylindre;
import com.vaitilingom.projetbackend.repository.formes.CylindreRepository;
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

    //Méthodes pragmatiques

    public double calculerSurface(Cylindre cylindre) {
        return cylindre.surface();
    }
    public double calculerVolume(Cylindre cylindre) {
        return cylindre.volume();
    }
}
