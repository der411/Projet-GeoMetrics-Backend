package com.vaitilingom.projetbackend.models.formes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.parents.Forme2d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cercle")

public class Cercle extends Forme2d {

    //Attribut
    @Column(name = "rayon")
    private Double rayon;

    @ManyToOne
    @JsonBackReference
    private User user;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Cercle(){
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Cercle(String nom, String couleur, double rayon, User user) {
        super(nom, couleur);
        this.rayon = rayon;
        this.user = user;
    }

    // méthodes implémentées
    public double circonference() {
        return 2 * Math.PI * rayon; // calcul de la circonférence du cercle
    }

    @Override
    public double perimetre() {
        return circonference(); // pour les cercles, le périmètre se nomme la circonférence
    }


    @Override
    public double surface() {
        return Math.PI * rayon * rayon; // calcul de la surface du cercle
    }

}