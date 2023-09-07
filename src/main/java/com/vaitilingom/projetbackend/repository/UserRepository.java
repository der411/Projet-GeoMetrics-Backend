package com.vaitilingom.projetbackend.repository;

import com.vaitilingom.projetbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String mail);
}
