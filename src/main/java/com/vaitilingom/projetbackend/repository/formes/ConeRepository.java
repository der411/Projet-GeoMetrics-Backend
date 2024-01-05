package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConeRepository extends JpaRepository<Cone, Integer> {

    List<Cone> findByUser(User user);
}
