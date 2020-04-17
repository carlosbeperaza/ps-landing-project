package com.ps.landing.project.repos;

import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.Role;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ps.landing.project.models.Role;


public interface RoleRepo extends CrudRepository<Role, Long>{
	
	Optional<Role> findByName(String name);
	Optional<Role> findByNameAndIdNot(String name, Long id);
}