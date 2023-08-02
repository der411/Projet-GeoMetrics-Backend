package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "triangle_scalene")

public class TriangleScalene extends Forme2d{

    @Column(name = "cote1")
    private Double cote1;

    @Column(name = "cote2")
    private Double cote2;

    @Column(name = "cote3")
    private Double cote3;

    //Constructeur par défaut
    public TriangleScalene(){
        super();
    }

    //Constructeur
    public TriangleScalene(String nom, String couleur, double cote1, double cote2, double cote3) {
        super(nom, couleur);
        this.cote1 = cote1;
        this.cote2 = cote2;
        this.cote3 = cote3;
    }

    //Méthodes implémentées
    @Override
    public double perimetre() {
        return cote1 + cote2 + cote3; // calcul du périmètre du triangle scalène
    }

    @Override
    public double surface() {
        double s = this.perimetre() / 2;
        return Math.sqrt(s * (s - cote1) * (s - cote2) * (s - cote3)); // Formule de Heron pour la surface du triangle scalène
    }

}