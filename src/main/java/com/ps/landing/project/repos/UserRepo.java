package com.ps.landing.project.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.models.Usuario;

public interface UserRepo extends CrudRepository<PSUser, Long> {
	
	public PSUser findByUsername(String username);

    @Query("SELECT u FROM PSUser u WHERE u.name = ?1")
    PSUser findByName(String name);
}
