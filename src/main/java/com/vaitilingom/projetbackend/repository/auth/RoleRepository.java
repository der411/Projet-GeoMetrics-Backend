package com.vaitilingom.projetbackend.repository.auth;

import com.vaitilingom.projetbackend.TypeDeRole;
import com.vaitilingom.projetbackend.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByLibelle(TypeDeRole libelle);
}
