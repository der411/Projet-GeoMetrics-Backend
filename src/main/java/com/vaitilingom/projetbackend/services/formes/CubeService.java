package com.vaitilingom.projetbackend.services.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.repository.formes.CubeRepository;
import com.vaitilingom.projetbackend.models.formes.Cube;
import com.vaitilingom.projetbackend.services.auth.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CubeService {
    //Attribut
    private final CubeRepository cubeRepository;
    private final UserService userService;

    //Constructeur
    public CubeService(CubeRepository cubeRepository, UserService userService) {
        this.cubeRepository = cubeRepository;
        this.userService = userService;
    }

    //Méthodes CRUD

    public List<Cube> getCubesByUser(User user) {
        return cubeRepository.findByUser(user);
    }

    public Optional<Cube> getCubeById(int id, User user) {
        Optional<Cube> cube = cubeRepository.findById(id);
        if (cube.isPresent() && cube.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à ce cube");
        }
        return cube;
    }


    public Cube addCube(Cube cube, User user) {
        cube.setUser(user);
        return cubeRepository.save(cube);
    }

    public Cube updateCube(Cube cube, User user) {
        cube.setUser(user);
        return cubeRepository.save(cube);
    }

    public void deleteCube(int id, User user) {
        Optional<Cube> cube = cubeRepository.findById(id);
        if (cube.isPresent() && cube.get().getUser() != user) {
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer ce cube");
        }
        cubeRepository.deleteById(id);
    }

    //Méthodes pragmatiques

    public double calculerSurface(Cube cube) {
        return cube.surface();
    }
    public double calculerVolume(Cube cube) {
        return cube.volume();
    }

    public Cube createCube(Cube cube, User user) {
        cube.setUser(user);
        Cube createdCube = cubeRepository.save(cube);
        return createdCube;
    }
}
