package com.ps.landing.project.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Role;


@Component("RoleConverter")
public class RoleConverter {
	
public Role convertToModel(RoleDTO dto) {
		
		

        Role role = new Role();
        

        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setStatus(dto.isStatus());
        role.setCreateDate((Timestamp) dto.getCreateDate());
        role.setLastUpdateDate((Timestamp) dto.getLastUpdateDate());

        return role;
    }

	public List<Role> convertToModel(List<RoleDTO> dtoList) {
	
	    List<Role> roles = new ArrayList<>();
	
	    for(RoleDTO roleDTO : dtoList) {
	
	        Role role = convertToModel(roleDTO);
	        roles.add(role);
	    }
	
	    return roles;
	}
	
	public RoleDTO convertToDTO(Role model) {

        RoleDTO roleDTO = new RoleDTO();
        
       roleDTO.setId(model.getId());
       roleDTO.setName(model.getName());
       roleDTO.setDescription(model.getDescription());
       roleDTO.setStatus(model.isStatus());
       roleDTO.setCreateDate(model.getCreateDate());
       roleDTO.setLastUpdateDate(model.getLastUpdateDate());

        return roleDTO;
    }

	public List<RoleDTO> convertToDTO(List<Role> roles) {

        List<RoleDTO> roleDTOS = new ArrayList<>();

        for(Role role : roles) {

            RoleDTO roleDTO = convertToDTO(role);
           roleDTOS.add(roleDTO);
        }

        return roleDTOS;
    }
	

}
