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

    public Carre(){
        super();
    }

    public Carre(String nom, String couleur, double cote) {
        super(nom, couleur); // on appelle le constructeur de la classe Rectangle avec la même valeur pour la longueur et la largeur
        this.cote = cote;
    }

    // méthodes
    public double perimetre() {
        return 4 * cote;
    }

    public double surface() {
        return cote * cote;
    }



}