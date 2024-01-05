package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
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
    private final CarreRepository carreRepository;
    private final UserService userService;

    //Constructeur
    public CarreService(CarreRepository carreRepository, UserService userService) {
        this.carreRepository = carreRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Carre> getCarresByUser(User user) {
        return carreRepository.findByUser(user);
    }

    public Optional<Carre> getCarreById(int id, User user) {
        Optional<Carre> carre = carreRepository.findById(id);
        if (carre.isPresent() && carre.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce carré");
        }
        return carre;
    }

    public Carre addCarre(Carre carre, User user) {
        carre.setUser(user);
        return carreRepository.save(carre);
    }

    public Carre updateCarre(Carre carre, User user) {
        carre.setUser(user);
        return carreRepository.save(carre);
    }

    public void deleteCarre(int id, User user) {
        Optional<Carre> carre = carreRepository.findById(id);
        if (carre.isPresent() && carre.get().getUser() != user) {
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

    public Carre createCarre(Carre carre, User user) {
        carre.setUser(user);
        Carre createdCarre = carreRepository.save(carre);
        return createdCarre;
    }
}
