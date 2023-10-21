package com.vaitilingom.projetbackend.models.auth;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.vaitilingom.projetbackend.TypeDeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TypeDeRole libelle;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();


    public Role(String libelle) {
        this.libelle = TypeDeRole.valueOf(libelle);
    }

    @JsonCreator
    public static Role fromString(String libelle) {
        return new Role(libelle);
    }

    @JsonValue
    public String toString() {
        return libelle.name();
    }
}
