package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Avis;
import com.vaitilingom.projetbackend.repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AvisService {

    private final AvisRepository avisRepository;

    public void creer(Avis avis){
        this.avisRepository.save(avis);
    }
}
