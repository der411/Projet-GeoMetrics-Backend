package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Forme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "couleur")
    private String couleur;

    @Column(name = "nom")
    private String nom;

    public Forme(){}

    public Forme(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    /**
     * Méthode abstraite à implementer, qui calcule la surface d'une forme (2d ou 3d)
     * @return la surface de la forme.
     */
    public abstract double surface();

}