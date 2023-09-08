package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.models.Role;
import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.models.Validation;
import com.vaitilingom.projetbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        String mdpCrypte = this.passwordEncoder.encode(user.getMdp());
        user.setMdp(mdpCrypte);

        Role roleUser = new Role();
        roleUser.setLibelle(TypeDeRole.UTILISATEUR);
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
        return this.userRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cette identifiant"));
    }
}
