package com.vaitilingom.projetbackend.models.formes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.parents.Forme3d;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cone")

public class Cone extends Forme3d {

    //Attributs
    @Column(name = "hauteur")
    private Double hauteur;

    @Column(name = "rayon")
    private Double rayon;

    @ManyToOne
    @JsonBackReference
    private User user;

    // constructeur par défaut (utilisé par l'ORM Hibernate)
    public Cone(){
        super();
    }

    // constructeur (non utilisé, mais conservé)
    public Cone(String nom, String couleur, Double hauteur, Double rayon, User user) {
        super(nom, couleur);
        this.hauteur = hauteur;
        this.rayon = rayon;
        this.user = user;
    }

    // méthodes implémentées
    @Override
    public double surface() {
        return Math.PI * rayon * (rayon + Math.sqrt(rayon * rayon + hauteur * hauteur));
    }

    @Override
    public double volume() {
        return Math.PI * rayon * rayon * hauteur / 3;
    }

}