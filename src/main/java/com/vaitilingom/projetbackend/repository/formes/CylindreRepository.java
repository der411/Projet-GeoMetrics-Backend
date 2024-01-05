package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Cylindre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CylindreRepository extends JpaRepository<Cylindre, Integer> {

    List<Cylindre> findByUser(User user);
}
