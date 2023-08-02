package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "losange")

public class Losange extends Forme2d{

    @Column(name = "cote")
    private Double cote;

    @Column(name = "grande_diag")
    private Double grandeDiag;

    @Column(name = "petite_diag")
    private Double petiteDiag;

    //Constructeur par défaut
    public Losange(){
        super();
    }

    //Constructeur
    public Losange(String nom, String couleur, double cote, double petiteDiag, double grandeDiag) {
        super(nom, couleur);
        this.cote= cote;
        this.petiteDiag = petiteDiag;
        this.grandeDiag = grandeDiag;
    }

    //Méthodes implémentées
    @Override
    public double perimetre() {
        return 4 * cote;
    }

    @Override
    public double surface() {
        return (grandeDiag * petiteDiag) / 2;
    }

}