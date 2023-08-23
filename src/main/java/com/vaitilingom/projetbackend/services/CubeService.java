package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.repository.CubeRepository;
import com.vaitilingom.projetbackend.models.Cube;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CubeService {
    //Attribut
    private final CubeRepository cubeRepository;

    //Constructeur
    public CubeService(CubeRepository cubeRepository) {
        this.cubeRepository = cubeRepository;
    }

    //Méthodes CRUD

    public List<Cube> getCubes() {
        return cubeRepository.findAll();
    }

    public Optional<Cube> getCubeById(int id) {
        return cubeRepository.findById(id);
    }

    public Cube addCube(Cube cube) {
        return cubeRepository.save(cube);
    }

    public Cube updateCube(Cube cube) {
        return cubeRepository.save(cube);
    }

    public void deleteCube(int id) {
        cubeRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Cube cube) {
        return cube.surface();
    }
    public double calculerVolume(Cube cube) {
        return cube.volume();
    }
}
