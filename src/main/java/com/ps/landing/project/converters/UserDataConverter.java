package com.ps.landing.project.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.UserDataDTO;
import com.ps.landing.project.models.PSUser;

@Component("UserDataConverter")
public class UserDataConverter {
	
	@Autowired
	private ModuleConverter moduleConverter;
	
	public PSUser UserDataDTOtoUser(UserDataDTO userDataDTO) {

		PSUser user = new PSUser();

		
		user.setName(userDataDTO.getName());
		user.setLastname(userDataDTO.getLastname());
		user.setEmail(userDataDTO.getEmail());
		user.setRegistrationDate(userDataDTO.getRegistrationDate());
		user.setUpdateDate(userDataDTO.getUpdateDate());
		user.setRoles(moduleConverter.convertToModel(userDataDTO.getModules()));

		return user;
	}
	
	public UserDataDTO UsertoUserDataDTO(PSUser user) {

		UserDataDTO userDTO = new UserDataDTO();

		
		userDTO.setName(user.getName());
		userDTO.setLastname(user.getLastname());
		userDTO.setEmail(user.getEmail());
		userDTO.setRegistrationDate(user.getRegistrationDate());
		userDTO.setUpdateDate(user.getUpdateDate());
		userDTO.setModules(ModuleConverter.convertToDTO(user.getModules()));
		return userDTO;

	}
	
	public List<UserDataDTO> convertToDTO(List<PSUser> users) {

		List<UserDataDTO> userDTOS = new ArrayList<>();

		for (PSUser user : users) {

			UserDataDTO userDTO = UsertoUserDataDTO(user);
			userDTOS.add(userDTO);
		}

		return userDTOS;
	}


}
