package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cercle")

public class Cercle extends Forme2d {

    @Column(name = "rayon")
    private Double rayon;

    // constructeur par défaut
    public Cercle(){
        super();
    }

    // constructeur
    public Cercle(String nom, String couleur, double rayon) {
        super(nom, couleur);
        this.rayon = rayon;
    }

    // méthodes implémentées
    @Override
    public double perimetre() {
        return 2 * Math.PI * rayon; // calcul du périmètre du cercle
    }

    @Override
    public double surface() {
        return Math.PI * rayon * rayon; // calcul de la surface du cercle
    }

}