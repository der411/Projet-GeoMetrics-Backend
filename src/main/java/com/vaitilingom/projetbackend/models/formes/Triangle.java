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
@Table(name = "triangle")

public class Triangle extends Forme2d {

    //Attributs
    @Column(name = "cote_adjacent")
    private Double coteAdjacent;

    @Column(name = "cote_oppose")
    private Double coteOppose;

    @ManyToOne
    @JsonBackReference
    private User user;

    //Constructeur par défaut (utilisé par l'ORM Hibernate)
    public Triangle(){
        super();
    }

    //Constructeur (non utilisé, mais conservé)
    public Triangle(String nom, String couleur, double coteAdjacent, double coteOppose) {
        super(nom, couleur);
        this.coteAdjacent = coteAdjacent;
        this.coteOppose = coteOppose;
    }

    // Hypoténuse n'est pas stockée car elle peut être calculée à partir des côtés adjacents et opposés.
    public double getHypotenuse() {
        return Math.sqrt(coteAdjacent * coteAdjacent + coteOppose * coteOppose);
    }

    //Méthodes implémentées
    @Override
    public double perimetre() {
        return coteAdjacent + coteOppose + getHypotenuse();
    }

    @Override
    public double surface() {
        return 0.5 * coteAdjacent * coteOppose;
    }
}
