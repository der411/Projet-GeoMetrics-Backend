package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cercle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CercleRepository extends JpaRepository<Cercle, Integer> {

    List<Cercle> findByUser(User user);
}
