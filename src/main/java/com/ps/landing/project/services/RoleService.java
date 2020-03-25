package com.ps.landing.project.services;


import java.util.List;

import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.exceptions.RoleException;
import com.ps.landing.project.models.Role;

public interface RoleService {

	List<RoleDTO> findAll();
    RoleDTO findById(long id);
    RoleDTO save(Role role) throws RoleException;
    RoleDTO update(Role role) throws RoleException;

    boolean disable(long id);
	
	
}
