package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme2d")
public abstract class Forme2d extends Forme {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    public Forme2d(){};

    // constructeur
    public Forme2d(String nom, String couleur) {
        super(nom, couleur);
    }
    /**
     * Méthode abstraite à implementer, qui calcule le périmètre d'une forme2d
     * * @return le perimètre de la forme2d.
     */
    public abstract double perimetre();
}