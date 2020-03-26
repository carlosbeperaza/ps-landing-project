package com.ps.landing.project.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Role;

@Component("RoleConverter")
public class RoleConverter {
	
	@Autowired
	private ModuleConverter moduleConverter;

	public Role convertToModel(RoleDTO roleDTO) {

		Role role = new Role();

		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setDescription(roleDTO.getDescription());
		role.setStatus(roleDTO.isStatus());
		role.setCreateDate((Timestamp) roleDTO.getCreateDate());
		role.setLastUpdateDate((Timestamp) roleDTO.getLastUpdateDate());
		role.setModules(moduleConverter.convertToModel(roleDTO.getModules()));

		return role;
	}

	public List<Role> convertToModel(List<RoleDTO> dtoList) {

		List<Role> roles = new ArrayList<>();

		for (RoleDTO roleDTO : dtoList) {

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
		roleDTO.setModules(moduleConverter.convertToDTO(model.getModules()));

		return roleDTO;
	}

	public List<RoleDTO> convertToDTO(List<Role> roles) {

		List<RoleDTO> roleDTOS = new ArrayList<>();

		for (Role role : roles) {

			RoleDTO roleDTO = convertToDTO(role);
			roleDTOS.add(roleDTO);
		}

		return roleDTOS;
	}

}
