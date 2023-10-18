package com.vaitilingom.projetbackend.models.formes;

import com.vaitilingom.projetbackend.models.parents.Forme3d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sphere")

public class Sphere extends Forme3d {

    //Attribut
    @Column(name = "rayon")
    private Double rayon;

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Sphere(){
        super();
    }

    //Constructeur (non utilisé, mais conservé)
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