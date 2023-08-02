package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cone")

public class Cone extends Forme3d {

    @Column(name = "hauteur")
    private Double hauteur;

    @Column(name = "rayon")
    private Double rayon;

    // constructeur par défaut
    public Cone(){
        super();
    }

    // constructeur
    public Cone(String nom, String couleur, double rayon, double hauteur) {
        super(nom, couleur);
        this.rayon = rayon;
        this.hauteur = hauteur;
    }

    // méthodes implémentées
    @Override
    public double surface() {
        return Math.PI * rayon * (rayon + Math.sqrt(rayon * rayon + hauteur * hauteur)); // calcul de la surface du cône
    }

    @Override
    public double volume() {
        return Math.PI * rayon * rayon * hauteur / 3; // calcul du volume du cône
    }

}