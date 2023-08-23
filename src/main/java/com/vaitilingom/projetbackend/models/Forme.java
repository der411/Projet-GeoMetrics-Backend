package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme")
@Inheritance(strategy = InheritanceType.JOINED) //JPA_Stratégie d'héritage

public abstract class Forme {
    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA_Stratégie de génération de l'identifiant
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "couleur")
    private String couleur;

    @Column(name = "nom")
    private String nom;

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Forme(){}

    //Constructeur (non utilisé, mais conservé)
    public Forme(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    //Méthode abstraite
    public abstract double surface(); //A implémenter dans les classes concrètes

}