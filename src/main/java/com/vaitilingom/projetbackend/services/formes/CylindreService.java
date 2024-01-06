package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cylindre;
import com.vaitilingom.projetbackend.repository.formes.CylindreRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CylindreService {

    //Attribut
    private final CylindreRepository cylindreRepository;
    private final UserService userService;

    //Constructeur
    public CylindreService(CylindreRepository cylindreRepository, UserService userService) {
        this.cylindreRepository = cylindreRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Cylindre> getCylindresByUser(User user) {
        return cylindreRepository.findByUser(user);
    }

    public Optional<Cylindre> getCylindreById(int id, User user) {
        Optional<Cylindre> cylindre = cylindreRepository.findById(id);
        if (cylindre.isPresent() && cylindre.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce cylindre");
        }
        return cylindre;
    }

    public Cylindre addCylindre(Cylindre cylindre, User user) {
        cylindre.setUser(user);
        return cylindreRepository.save(cylindre);
    }

    public Cylindre updateCylindre(Cylindre cylindre, User user) {
        cylindre.setUser(user);
        return cylindreRepository.save(cylindre);
    }

    public void deleteCylindre(int id, User user) {
        Optional<Cylindre> cylindre = cylindreRepository.findById(id);
        if (cylindre.isPresent() && cylindre.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce cylindre");
        }
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
