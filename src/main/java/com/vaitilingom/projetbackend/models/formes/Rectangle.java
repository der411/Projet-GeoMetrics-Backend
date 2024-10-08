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
@Table(name = "rectangle")

public class Rectangle extends Forme2d {

    //Attributs
    @Column(name = "largeur")
    private Double largeur;

    @Column(name = "longueur")
    private Double longueur;

    @ManyToOne
    @JsonBackReference
    private User user;

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Rectangle(){
        super();
    }

    //Constructeur (non utilisé, mais conservé)
    public Rectangle(String nom, String couleur, double longueur, double largeur) {
        super(nom, couleur);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    // Méthodes implémentées
    public double perimetre() {
        return 2 * (longueur + largeur); // le périmètre d'un rectangle est égal à deux fois la somme de la longueur et de la largeur
    }

    public double surface() {
        return longueur * largeur; // la surface d'un rectangle est égale au produit de la longueur et de la largeur
    }

}