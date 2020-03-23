package com.ps.landing.project.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.models.PSUser;

@Component("UserConverter")
public class UserConverter {

	@Autowired
	private RoleConverter roleConverter;

	public PSUser UserDTOtoUser(UserDTO userDTO) {

		PSUser user = new PSUser();

		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setStatus(userDTO.isStatus());
		user.setRegistrationDate(userDTO.getRegistrationDate());
		user.setUpdateDate(userDTO.getUpdateDate());
		user.setRoles(roleConverter.convertToModel(userDTO.getRoles()));

		return user;
	}

	public UserDTO UsertoUserDTO(PSUser user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setUsername(user.getUsername());
		userDTO.setLastname(user.getLastname());
		userDTO.setEmail(user.getEmail());
//			 userDTO.setPassword( user.getPassword());
		userDTO.setStatus(user.isStatus());
//		userDTO.setRegistrationDate(user.getRegistrationDate());
//		userDTO.setUpdateDate(user.getUpdateDate());
		userDTO.setRoles(roleConverter.convertToDTO(user.getRoles()));
		return userDTO;

	}

	public List<UserDTO> convertToDTO(List<PSUser> users) {

		List<UserDTO> userDTOS = new ArrayList<>();

		for (PSUser user : users) {

			UserDTO userDTO = UsertoUserDTO(user);
			userDTOS.add(userDTO);
		}

		return userDTOS;
	}
}
