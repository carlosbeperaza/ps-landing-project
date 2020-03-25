package com.ps.landing.project.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.models.Usuario;

public interface UserRepo extends CrudRepository<PSUser, Long> {

    Optional<PSUser> findByName(String name);
    Optional<PSUser> findByUsername(String username);
    Optional<PSUser> findByEmail(String email);
    Iterable<PSUser> findByUsernameOrEmail(String username, String email);
}
