package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Carre;
import com.vaitilingom.projetbackend.models.Sphere;
import com.vaitilingom.projetbackend.repository.SphereRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SphereService {
    //Attribut
    private final SphereRepository sphereRepository;

    //Constructeur
    public SphereService(SphereRepository sphereRepository) {
        this.sphereRepository = sphereRepository;
    }

    //Méthodes CRUD
    public List<Sphere> getSpheres() {
        return sphereRepository.findAll();
    }

    public Optional<Sphere> getSphereById(int id) {
        return sphereRepository.findById(id);
    }

    public Sphere addSphere(Sphere sphere) {
        return sphereRepository.save(sphere);
    }

    public Sphere updateSphere(Sphere sphere) {
        return sphereRepository.save(sphere);
    }

    public void deleteSphere(int id) {
        sphereRepository.deleteById(id);
    }

    //Méthodes spécifiques à la Sphère

    public double calculerSurface(Sphere sphere) {
        return sphere.surface();
    }
    public double calculerVolume(Sphere sphere) {
        return sphere.volume();
    }
}
