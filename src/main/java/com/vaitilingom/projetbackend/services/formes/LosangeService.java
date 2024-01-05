package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Losange;
import com.vaitilingom.projetbackend.repository.formes.LosangeRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LosangeService {
    //Attribut
    private final LosangeRepository losangeRepository;
    private final UserService userService;

    //Constructeur
    public LosangeService(LosangeRepository losangeRepository, UserService userService) {
        this.losangeRepository = losangeRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Losange> getLosangesByUser(User user) {
        return losangeRepository.findByUser(user);
    }

    public Optional<Losange> getLosangeById(int id, User user) {
        Optional<Losange> losange = losangeRepository.findById(id);
        if (losange.isPresent() && losange.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce losange");
        }
        return losange;
    }

    public Losange addLosange(Losange losange, User user) {
        losange.setUser(user);
        return losangeRepository.save(losange);
    }

    public Losange updateLosange(Losange losange, User user) {
        losange.setUser(user);
        return losangeRepository.save(losange);
    }

    public void deleteLosange(int id, User user) {
        Optional<Losange> losange = losangeRepository.findById(id);
        if (losange.isPresent() && losange.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce losange");
        }
        losangeRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerPerimetre(Losange losange) { return losange.perimetre();}

    public double calculerSurface(Losange losange) {
        return losange.surface();
    }

    public Losange createLosange(Losange losange, User user) {
        losange.setUser(user);
        Losange createdLosange = losangeRepository.save(losange);
        return createdLosange;
    }
}
