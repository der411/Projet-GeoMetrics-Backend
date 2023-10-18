package com.vaitilingom.projetbackend.services.auth;

import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.models.auth.Role;
import com.vaitilingom.projetbackend.models.auth.User;
import com.vaitilingom.projetbackend.models.auth.Validation;
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

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));
        if(Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }
        User userActive = this.userRepository.findById(validation.getUser().getId()).orElseThrow(( )-> new RuntimeException("Utilisateur inconnu"));
        userActive.setActif(true);
        this.userRepository.save(userActive);
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

        Role roleAdmin = new Role();
        roleAdmin.setLibelle(TypeDeRole.ADMINISTRATEUR);
        user.setRole(roleAdmin);

        this.userRepository.save(user);
    }
}
