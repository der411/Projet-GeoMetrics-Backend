package com.vaitilingom.projetbackend.models.formes;

import com.vaitilingom.projetbackend.models.parents.Forme3d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cube")

public class Cube extends Forme3d {

    //Attribut
    @Column(name = "cote")
    private Double cote;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Cube() {
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Cube(String nom, String couleur, double cote) {
        super(nom, couleur);
        this.cote = cote;
    }

    // méthodes implémentées
    @Override
    public double surface() {
        return 6 * cote * cote;
    }

    @Override
    public double volume() {
        return cote * cote * cote;
    }

}