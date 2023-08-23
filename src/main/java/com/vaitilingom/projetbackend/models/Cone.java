package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cone")

public class Cone extends Forme3d {

    //Attributs
    @Column(name = "hauteur")
    private Double hauteur;

    @Column(name = "rayon")
    private Double rayon;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Cone(){
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Cone(String nom, String couleur, Double hauteur, Double rayon) {
        super(nom, couleur);
        this.hauteur = hauteur;
        this.rayon = rayon;
    }

    // méthodes implémentées
    @Override
    public double surface() {
        return Math.PI * rayon * (rayon + Math.sqrt(rayon * rayon + hauteur * hauteur));
    }

    @Override
    public double volume() {
        return Math.PI * rayon * rayon * hauteur / 3;
    }

}