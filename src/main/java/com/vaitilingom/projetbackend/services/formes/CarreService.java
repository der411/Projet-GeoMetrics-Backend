package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.repository.formes.CarreRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreService {
    //Attribut
    private CarreRepository carreRepository;
    private final UserService userService;


    //Constructeur
    public CarreService(CarreRepository carreRepository, UserService userService) {
        this.carreRepository = carreRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Carre> getCarresByUserId(Integer userId) {
        return carreRepository.findByUserId(userId);
    }

    public Optional<Carre> getCarreById(int id, Integer userId) {
        Optional<Carre> carre = carreRepository.findById(id);
        if (carre.isPresent() && carre.get().getUserId() != userId) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce carré");
        }
        return carre;
    }

    public Carre addCarre(Carre carre, Integer userId) {
        carre.setUserId(userId);
        return carreRepository.save(carre);
    }

    public Carre updateCarre(Carre carre, Integer userId) {
        carre.setUserId(userId);
        return carreRepository.save(carre);
    }

    public void deleteCarre(int id, Integer userId) {
        Optional<Carre> carre = carreRepository.findById(id);
        if (carre.isPresent() && carre.get().getUserId() != userId) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce carré");
        }
        carreRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Carre carre) {
        return carre.surface();
    }

    public double calculerPerimetre(Carre carre) {
        return carre.perimetre();
    }

    public Carre createCarre(Carre carre, int userId) {
        // Créez un nouveau Carre dans votre base de données
        // Vous devrez probablement utiliser une méthode de votre repository pour faire cela
        Carre createdCarre = carreRepository.save(carre);

        // Associez le Carre créé à l'utilisateur spécifié
        // Vous devrez probablement utiliser une méthode de votre repository pour faire cela
        userService.addCarreToUser(createdCarre, userId);

        // Renvoyez l'objet Carre créé
        return createdCarre;
    }
}
