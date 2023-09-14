package com.vaitilingom.projetbackend.controller;

import com.vaitilingom.projetbackend.models.Avis;
import com.vaitilingom.projetbackend.services.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class AvisController {

    private final AvisService avisService;

    @PostMapping("/avis")
    public void creer(@RequestBody Avis avis){
        this.avisService.creer(avis);
    }
}
