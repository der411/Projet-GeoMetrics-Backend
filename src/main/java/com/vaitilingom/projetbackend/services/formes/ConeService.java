package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cone;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import com.vaitilingom.projetbackend.repository.formes.ConeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConeService {

    //Attribut
    private final ConeRepository coneRepository;
    private final UserService userService;

    //Constructeur
    public ConeService(ConeRepository coneRepository, UserService userService) {
        this.coneRepository = coneRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Cone> getConesByUser(User user) {
        return coneRepository.findByUser(user);
    }

    public Optional<Cone> getConeById(int id, User user) {
        Optional<Cone> cone = coneRepository.findById(id);
        if (cone.isPresent() && cone.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce cone");
        }
        return cone;
    }

    public Cone addCone(Cone cone, User user) {
        cone.setUser(user);
        return coneRepository.save(cone);
    }

    public Cone updateCone(Cone cone, User user) {
        cone.setUser(user);
        return coneRepository.save(cone);
    }

    public void deleteCone(int id, User user) {
        Optional<Cone> cone = coneRepository.findById(id);
        if (cone.isPresent() && cone.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce cone");
        }
        coneRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Cone cone) {
        return cone.surface();
    }

    public double calculerVolume(Cone cone) {
        return cone.volume();
    }

}
