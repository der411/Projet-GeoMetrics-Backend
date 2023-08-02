package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cube")

public class Cube extends Forme3d{

    @Column(name = "cote")
    private Double cote;

    // constructeur par défaut
    public Cube() {
        super();
    }

    // constructeur
    public Cube(String nom, String couleur, double cote) {
        super(nom, couleur);
        this.cote = cote;
    }

    // méthodes implémentées
    @Override
    public double surface() {
        return 6 * cote * cote; // calcul de la surface du cube
    }

    @Override
    public double volume() {
        return cote * cote * cote; // calcul du volume du cube
    }

}