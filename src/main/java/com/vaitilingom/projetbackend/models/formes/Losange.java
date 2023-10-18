package com.vaitilingom.projetbackend.models.formes;

import com.vaitilingom.projetbackend.models.parents.Forme2d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "losange")

public class Losange extends Forme2d {

    //Attributs
    @Column(name = "cote")
    private Double cote;

    @Column(name = "grande_diag")
    private Double grandeDiag;

    @Column(name = "petite_diag")
    private Double petiteDiag;

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Losange(){
        super();
    }

    //Constructeur (non utilisé, mais conservé)
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