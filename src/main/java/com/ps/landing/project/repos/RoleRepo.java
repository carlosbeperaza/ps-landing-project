package com.ps.landing.project.repos;


import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.Role;


public interface RoleRepo extends CrudRepository<Role, Long>{
	
	
}