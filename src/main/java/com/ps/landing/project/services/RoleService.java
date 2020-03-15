package com.ps.landing.project.services;


import java.util.List;

import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Role;

public interface RoleService {

	List<RoleDTO> findAll();
    RoleDTO findById(long id);
    RoleDTO save(Role role);
    RoleDTO update(Role role);

    boolean disable(long id);
	
	
}
