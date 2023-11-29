package com.vaitilingom.projetbackend.services.auth;

import com.sun.istack.NotNull;
import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.ValidationRequestDTO;
import com.vaitilingom.projetbackend.models.auth.Role;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.auth.Validation;
import com.vaitilingom.projetbackend.models.formes.Carre;
import com.vaitilingom.projetbackend.repository.auth.RoleRepository;
import com.vaitilingom.projetbackend.repository.auth.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    private RoleRepository roleRepository;


    public User inscription(@NotNull User user){
        // Validation de l'e-mail
        if(!user.getMail().contains("@") || !user.getMail().contains(".")) {
            throw new RuntimeException("Email invalide");
        }
        Optional<User> userOptional = this.userRepository.findByMail(user.getMail());
        if(userOptional.isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        String passWordCrypte = this.passwordEncoder.encode(user.getPassword());
        user.setPassWord(passWordCrypte);

        // Recherche du rôle utilisateur
        Optional<Role> userRoleOptional = roleRepository.findByLibelle(TypeDeRole.USER);
        if (!userRoleOptional.isPresent()) {
            // Si le rôle n'existe pas, créez-le.
            Role newUserRole = new Role();
            newUserRole.setLibelle(TypeDeRole.USER);
            userRoleOptional = Optional.of(roleRepository.save(newUserRole));
        }

        // Associer l'utilisateur avec le rôle
        user.getRoles().add(userRoleOptional.get());

        // Sauvegarde de l'utilisateur
        user = this.userRepository.save(user);

        // Votre logique d'enregistrement de validation
        this.validationService.enregistrer(user);

        return user;
    }


    public boolean activation(ValidationRequestDTO activationRequest) {
        try {
            Validation validation = this.validationService.lireEnFonctionDuCode(activationRequest.getCode());
            if (Instant.now().isAfter(validation.getExpiration())) {
                throw new RuntimeException("Votre code a expiré");
            }

            User userActive = this.userRepository.findById(validation.getUser().getId()).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));

            if (userActive.isActif()) {
                return false;  // Si l'utilisateur est déjà actif, retournez false.
            } else {
                userActive.setActif(true);
                this.userRepository.save(userActive);
                return true;  // Si l'utilisateur a été activé avec succès, retournez true.
            }
        } catch (RuntimeException e) {
            // Vous pouvez logger l'erreur ici si nécessaire
            // Vous pourriez aussi retourner une ResponseEntity avec un code d'état et un message d'erreur appropriés
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = this.userRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cette identifiant"));

        // Ici, nous récupérons tous les rôles de l'utilisateur pour les convertir en authorities
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getLibelle().name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), authorities);
    }

    public User findByMail(String mail) {
        UserDetails userDetails = this.loadUserByUsername(mail);
        return userRepository.findByMail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + mail));
    }

    public boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getLibelle() == TypeDeRole.ADMINISTRATEUR) {
                return true;
            }
        }
        return false;
    }

    public User getUserData(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    }

    @Transactional
    public void setAdminRoleToUser(String mail){
        User user = this.userRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'e-mail: " + mail));

        Role roleAdmin = roleRepository.findByLibelle(TypeDeRole.ADMINISTRATEUR)
                .orElseThrow(() -> new RuntimeException("Rôle ADMINISTRATEUR introuvable"));

        // Avec ManyToMany, ajoutez le rôle à la liste des rôles de l'utilisateur
        user.getRoles().add(roleAdmin);

        this.userRepository.save(user);
    }

    public void addCarreToUser(Carre carre, int userId) {
        // Récupérez l'utilisateur à partir de la base de données
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID Utilisateur Invalide:" + userId));

        // Associez le carré à l'utilisateur
        user.getCarres().add(carre);

        // Enregistrez l'utilisateur dans la base de données
        userRepository.save(user);
    }

}
