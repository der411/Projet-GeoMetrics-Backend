package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme3d")
public abstract class Forme3d extends Forme {

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Forme3d(){}

    //Constructeur (non utilisé, mais conservé)
    public Forme3d(String nom, String couleur) {
        super(nom, couleur);
    }

    //Méthode abstraite
    public abstract double volume(); //A implémenter dans les classes concrètes héritières

}