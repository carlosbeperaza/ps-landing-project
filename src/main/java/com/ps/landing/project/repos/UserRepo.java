package com.ps.landing.project.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.models.Usuario;

public interface UserRepo extends CrudRepository<PSUser, Long> {
	
	public PSUser findByUsername(String username);

    @Query("SELECT u FROM PSUser u WHERE u.name = ?1")
    PSUser findByName(String name);
    
    Optional<PSUser> findFirstByUsernameOrEmail (String username, String email);
    
    Optional<PSUser> findFirstByUsernameOrEmailAndIdNot (String username,String email,Long id);
    
    @Query(value = "SELECT * FROM users WHERE (user_name = ?1 OR email = ?2) AND id != ?3 LIMIT 1", nativeQuery=true)
    Optional<PSUser> a(String username,String email,Long id);
    
    @Query("SELECT u FROM PSUser u WHERE (u.name = ?1 OR u.email = ?2) AND id != ?3")
    Optional<PSUser> b(String username,String email,Long id);
    
    Optional<PSUser> findByUsernameAndIdNot (String username, Long id);
    
    Optional<PSUser> findByEmailAndIdNot (String email, Long id);
    
}
