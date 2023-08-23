package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme2d")
public abstract class Forme2d extends Forme {

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Forme2d(){};

    //Constructeur (non utilisé, mais conservé)
    public Forme2d(String nom, String couleur) {
        super(nom, couleur);
    }

    //Méthode abstraite
    public abstract double perimetre(); //A implémenter dans les classes concrètes héritières
}