package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Carre;
import com.vaitilingom.projetbackend.repository.CarreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreService {
    //Attribut
    private CarreRepository carreRepository;

    //Constructeur
    public CarreService(CarreRepository carreRepository) {
        this.carreRepository = carreRepository;
    }

    //Méthodes CRUD

    public List<Carre> getCarres() {
        return carreRepository.findAll();
    }

    public Optional<Carre> getCarreById(int id) {
        return carreRepository.findById(id);
    }

    public Carre addCarre(Carre carre) {
        return carreRepository.save(carre);
    }

    public Carre updateCarre(Carre carre) {
        return carreRepository.save(carre);
    }

    public void deleteCarre(int id) {
        carreRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Carre carre) {
        return carre.surface();
    }

    public double calculerPerimetre(Carre carre) {
        return carre.perimetre();
    }

}
