package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sphere")

public class Sphere extends Forme3d{

    @Column(name = "rayon")
    private Double rayon;

    //Constructeur par défaut
    public Sphere(){
        super();
    }
    //Constructeur
    public Sphere(String nom, String couleur, double rayon) {
        super(nom, couleur);
        this.rayon = rayon;
    }

    //Méthodes implémentées
    @Override
    public double surface() {
        return 4 * Math.PI * rayon * rayon;
    }

    @Override
    public double volume() {
        return (4 * Math.PI * rayon * rayon * rayon) / 3;
    }

}