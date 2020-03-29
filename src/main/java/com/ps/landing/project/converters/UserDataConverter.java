package com.ps.landing.project.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.UserDataDTO;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.models.Module;


@Component("UserDataConverter")
public class UserDataConverter {
	
	@Autowired
	private ModuleConverter moduleConverter;
		
	public UserDataDTO UsertoUserDataDTO(PSUser user) {

		UserDataDTO userDTO = new UserDataDTO();
		List<Module> listaModules = new ArrayList<>();
		
		
		for(Role role:user.getRoles()) {
			for(Module module:role.getModules()) {
				listaModules.add(module);
			}
		}

		
		userDTO.setName(user.getName());
		userDTO.setLastname(user.getLastname());
		userDTO.setEmail(user.getEmail());
		userDTO.setRegistrationDate(user.getRegistrationDate());
		userDTO.setUpdateDate(user.getUpdateDate());
		userDTO.setModules(moduleConverter.convertToDTO(listaModules));
		
		
		return userDTO;

	}
	
	


}
