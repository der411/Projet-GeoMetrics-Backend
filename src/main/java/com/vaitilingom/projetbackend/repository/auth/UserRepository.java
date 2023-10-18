package com.vaitilingom.projetbackend.repository.auth;

import com.vaitilingom.projetbackend.models.auth.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByMail(String mail);
}
