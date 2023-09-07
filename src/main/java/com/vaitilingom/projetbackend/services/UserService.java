package com.vaitilingom.projetbackend.services;

import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.models.Role;
import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public void inscription(User user){
        if(!user.getMdp().contains("@")) {
            throw new RuntimeException("Email invalide");
        }
        if(!user.getMdp().contains(".")) {
            throw new RuntimeException("Email invalide");
        }
        Optional<User> userOptional = this.userRepository.findByUsername(user.getMail());
        if(userOptional.isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        String mdpCrypte = this.passwordEncoder.encode(user.getMdp());
        user.setMdp(mdpCrypte);

        Role roleUser = new Role();
        roleUser.setLibelle(TypeDeRole.UTILISATEUR);
        user.setRole(roleUser);

        this.userRepository.save(user);
    }

}
