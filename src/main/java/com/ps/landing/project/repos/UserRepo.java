package com.ps.landing.project.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.models.Usuario;

public interface UserRepo extends CrudRepository<PSUser, Long> {

    Optional<PSUser> findByName(String name);
    Optional<PSUser> findByUsername(String username);
    Optional<PSUser> findByUsernameAndIdNot(String username, Long id);
    Optional<PSUser> findByEmail(String email);
    Optional<PSUser> findByEmailAndIdNot(String email, Long id);

    //TODO: borrar, ya no son de utilidad.
    Optional<PSUser> findFirstByUsernameOrEmail (String username, String email);

    @Query(value = "SELECT * FROM users WHERE (user_name = ?1 OR email = ?2) AND id != ?3 LIMIT 1", nativeQuery=true)
    Optional<PSUser> a(String username,String email,Long id);
    
    @Query("SELECT u FROM PSUser u WHERE (u.name = ?1 OR u.email = ?2) AND u.id <> ?3")
    Optional<PSUser> b(String username,String email,Long id);
}
