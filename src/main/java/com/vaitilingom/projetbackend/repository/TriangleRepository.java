package com.vaitilingom.projetbackend.repository;

import com.vaitilingom.projetbackend.models.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriangleRepository extends JpaRepository<Triangle, Integer> {
}
