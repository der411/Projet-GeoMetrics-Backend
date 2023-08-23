package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cylindre")

public class Cylindre extends Forme3d {

    //Attributs
    @Column(name = "hauteur")
    private Double hauteur;

    @Column(name = "rayon")
    private Double rayon;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Cylindre(){
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Cylindre(String nom, String couleur, double rayon, double hauteur) {
        super(nom, couleur);
        this.rayon = rayon;
        this.hauteur = hauteur;
    }

    //Méthodes implémentées
    @Override
    public double surface() {
        return 2 * Math.PI * rayon * rayon + 2 * Math.PI * rayon * hauteur;
    }

    @Override
    public double volume() {
        return Math.PI * rayon * rayon * hauteur;
    }

}