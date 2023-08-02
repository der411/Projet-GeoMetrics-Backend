package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.models.Losange;
import com.vaitilingom.projetbackend.repository.LosangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LosangeService {
    //Attribut
    private final LosangeRepository losangeRepository;

    //Constructeur
    public LosangeService(LosangeRepository losangeRepository) {
        this.losangeRepository = losangeRepository;
    }

    //Méthodes CRUD

    public List<Losange> getLosanges() {
        return losangeRepository.findAll();
    }

    public Optional<Losange> getLosangeById(int id) {
        return losangeRepository.findById(id);
    }

    public Losange addLosange(Losange losange) {
        return losangeRepository.save(losange);
    }

    public Losange updateLosange(Losange losange) {
        return losangeRepository.save(losange);
    }

    public void deleteLosange(int id) {
        losangeRepository.deleteById(id);
    }
}
