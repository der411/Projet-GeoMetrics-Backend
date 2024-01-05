package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TriangleRepository extends JpaRepository<Triangle, Integer> {

    List<Triangle> findByUser(User user);
}
