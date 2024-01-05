package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cercle;
import com.vaitilingom.projetbackend.repository.formes.CercleRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CercleService {
    //Attribut
    private final CercleRepository cercleRepository;
    private final UserService userService;

    //Constructeur
    public CercleService(CercleRepository cercleRepository, UserService userService) {
        this.cercleRepository = cercleRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Cercle> getCerclesByUser(User user) {
        return cercleRepository.findByUser(user);
    }

    public Optional<Cercle> getCercleById(int id, User user) {
        Optional<Cercle> cercle = cercleRepository.findById(id);
        if (cercle.isPresent() && cercle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce cercle");
        }
        return cercle;
    }

    public Cercle addCercle(Cercle cercle, User user) {
        cercle.setUser(user);
        return cercleRepository.save(cercle);
    }

    public Cercle updateCercle(Cercle cercle, User user) {
        cercle.setUser(user);
        return cercleRepository.save(cercle);
    }

    public void deleteCercle(int id, User user) {
        Optional<Cercle> cercle = cercleRepository.findById(id);
        if (cercle.isPresent() && cercle.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce cercle");
        }
        cercleRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Cercle cercle) {
        return cercle.surface();
    }
    public double calculerCirconference(Cercle cercle) {
        return cercle.circonference();
    }

    public Cercle createCercle(Cercle cercle, User user) {
        cercle.setUser(user);
        Cercle createdCercle = cercleRepository.save(cercle);
        return createdCercle;
    }
}
