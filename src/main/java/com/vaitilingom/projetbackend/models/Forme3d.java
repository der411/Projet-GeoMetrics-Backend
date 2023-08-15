package com.vaitilingom.projetbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forme3d")
public abstract class Forme3d extends Forme {

    public Forme3d(){}

    public Forme3d(String nom, String couleur) {
        super(nom, couleur);
    }
    /**
     * Méthode abstraite à implementer, qui calcule le volume d'une forme3d
     * * @return le volume de la forme3d.
     */
    public abstract double volume();

}