package com.vaitilingom.projetbackend.models.formes;

import com.vaitilingom.projetbackend.models.parents.Forme2d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carre")

public class Carre extends Forme2d {

    //Attribut
    @Column(name = "cote")
    private Double cote;

    @Column(name = "user_id")
    private Integer userId;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Carre(){
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Carre(String nom, String couleur, double cote, Integer userId){
        super(nom, couleur);
        this.cote = cote;
        this.userId = userId;
    }

    // méthodes implémentées
    public double perimetre() {
        return 4 * cote;
    }

    public double surface() {
        return cote * cote;
    }
}