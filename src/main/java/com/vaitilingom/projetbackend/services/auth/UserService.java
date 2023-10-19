package com.vaitilingom.projetbackend.services.auth;

import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.models.auth.Role;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.auth.Validation;
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

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    private RoleRepository roleRepository;
    public void inscription(User user){
        if(!user.getMail().contains("@")) {
            throw new RuntimeException("Email invalide");
        }
        if(!user.getMail().contains(".")) {
            throw new RuntimeException("Email invalide");
        }
        Optional<User> userOptional = this.userRepository.findByMail(user.getMail());
        if(userOptional.isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        String passWordCrypte = this.passwordEncoder.encode(user.getPassword());
        user.setPassWord(passWordCrypte);

        Role roleUser = new Role();
        roleUser.setLibelle(TypeDeRole.USER);
        user.setRole(roleUser);

        user = this.userRepository.save(user);
        this.validationService.enregistrer(user);
    }

    public boolean activation(Map<String, String> activation) {
        try {
            Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));
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

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getLibelle().name()));

        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), authorities);
    }

    @Transactional
    public void setAdminRoleToUser(String mail){
        User user = this.userRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'e-mail: " + mail));

        Role roleAdmin = roleRepository.findByLibelle(TypeDeRole.ADMINISTRATEUR)
                .orElseThrow(() -> new RuntimeException("Rôle ADMINISTRATEUR introuvable"));

        user.setRole(roleAdmin);

        this.userRepository.save(user);
    }
}
