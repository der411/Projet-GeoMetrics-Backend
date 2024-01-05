package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CubeRepository extends JpaRepository<Cube, Integer> {

    List<Cube> findByUser(User user);
}
