package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.models.formes.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RectangleRepository extends JpaRepository<Rectangle, Integer> {

    List<Rectangle> findByUser(User user);
}
