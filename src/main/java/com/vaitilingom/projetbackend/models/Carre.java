package com.vaitilingom.projetbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carre")

public class Carre extends Forme2d {

    @Column(name = "cote")
    private Double cote;

    // constructeur par défaut
    public Carre(){
        super();
    }

    // constructeur
    public Carre(String nom, String couleur, double cote) {
        super(nom, couleur);
        this.cote = cote;
    }

    // méthodes implémentées
    public double perimetre() {
        return 4 * cote;
    }

    public double surface() {
        return cote * cote;
    }
}