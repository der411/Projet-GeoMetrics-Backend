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
    public double circonference() {
        return 2 * Math.PI * rayon; // calcul de la circonférence du cercle
    }
    @Override
    public double perimetre() {
        return circonference(); // pour les cercles, le périmètre est en fait la circonférence
    }


    @Override
    public double surface() {
        return Math.PI * rayon * rayon; // calcul de la surface du cercle
    }

}