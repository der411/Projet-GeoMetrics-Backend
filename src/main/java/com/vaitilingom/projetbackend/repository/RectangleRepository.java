package com.vaitilingom.projetbackend.repository;

import com.vaitilingom.projetbackend.models.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle, Integer> {
}
