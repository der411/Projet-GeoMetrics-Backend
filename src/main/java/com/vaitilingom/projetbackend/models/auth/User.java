package com.vaitilingom.projetbackend.models.auth;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vaitilingom.projetbackend.models.formes.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utilisateur")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="mail")
    private String mail;
    @Column(name="mot_de_passe")
    private String passWord;
    private boolean actif = false;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Carre> carres;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Cercle> cercles;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Cone> cones;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Cube> cubes;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Cylindre> cylindres;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Losange> losanges;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Rectangle> rectangles;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Sphere> spheres;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Triangle> triangles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getLibelle()))
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }

}
