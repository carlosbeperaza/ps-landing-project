package com.ps.landing.project.services;


import com.ps.landing.project.models.Role;

public interface RoleService {

	// Rest
	
	Role findById(long id);
    Role save();
    Role modify(long id);
    void delete(long id);
	
	
}
