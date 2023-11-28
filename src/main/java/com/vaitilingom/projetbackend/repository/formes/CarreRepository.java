package com.vaitilingom.projetbackend.repository.formes;

import com.vaitilingom.projetbackend.models.formes.Carre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarreRepository extends JpaRepository<Carre, Integer> {

        List<Carre> findByUserId(Integer userId);

}
