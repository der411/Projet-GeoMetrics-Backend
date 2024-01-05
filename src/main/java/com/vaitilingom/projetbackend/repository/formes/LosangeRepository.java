package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Losange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LosangeRepository extends JpaRepository<Losange, Integer> {

    List<Losange> findByUser(User user);
}
