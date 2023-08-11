package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "triangle")

public class Triangle extends Forme2d{

    @Column(name = "cote_adjacent")
    private Double coteAdjacent;

    @Column(name = "cote_oppose")
    private Double coteOppose;

    // Hypoténuse n'est pas stockée car elle peut être calculée à partir des côtés adjacents et opposés.
    public double getHypotenuse() {
        return Math.sqrt(coteAdjacent * coteAdjacent + coteOppose * coteOppose);
    }

    //Constructeur par défaut
    public Triangle(){
        super();
    }

    //Constructeur
    public Triangle(String nom, String couleur, double coteAdjacent, double coteOppose) {
        super(nom, couleur);
        this.coteAdjacent = coteAdjacent;
        this.coteOppose = coteOppose;
    }

    //Méthodes implémentées
    @Override
    public double perimetre() {
        return coteAdjacent + coteOppose + getHypotenuse();
    }

    @Override
    public double surface() {
        return 0.5 * coteAdjacent * coteOppose;
    }
}
