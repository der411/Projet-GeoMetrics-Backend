package com.vaitilingom.projetbackend.services.avis;

import com.vaitilingom.projetbackend.models.avis.Avis;
import com.vaitilingom.projetbackend.repository.avis.AvisRepository;
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
