package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Cone;
import com.vaitilingom.projetbackend.models.formes.Sphere;
import com.vaitilingom.projetbackend.repository.formes.SphereRepository;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SphereService {
    //Attribut
    private final SphereRepository sphereRepository;
    private final UserService userService;

    //Constructeur
    public SphereService(SphereRepository sphereRepository, UserService userService) {
        this.sphereRepository = sphereRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Sphere> getSpheresByUser(User user) {
        return sphereRepository.findByUser(user);
    }

    public Optional<Sphere> getSphereById(int id, User user) {
        Optional<Sphere> sphere = sphereRepository.findById(id);
        if (sphere.isPresent() && sphere.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à cette sphere");
        }
        return sphere;
    }

    public Sphere addSphere(Sphere sphere, User user) {
        sphere.setUser(user);
        return sphereRepository.save(sphere);
    }

    public Sphere updateSphere(Sphere sphere, User user) {
        sphere.setUser(user);
        return sphereRepository.save(sphere);
    }

    public void deleteSphere(int id, User user) {
        Optional<Sphere> sphere = sphereRepository.findById(id);
        if (sphere.isPresent() && sphere.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer cette sphere");
        }
        sphereRepository.deleteById(id);
    }
    //Méthodes pragmatiques

    public double calculerSurface(Sphere sphere) {
        return sphere.surface();
    }

    public double calculerVolume(Sphere sphere) {
        return sphere.volume();
    }
}
