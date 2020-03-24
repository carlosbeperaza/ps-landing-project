package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;

public interface UserService {

	List<UserDTO> findAll();

	UserDTO save(PSUser user, String passwordBcrypt) throws UserException;

	UserDTO findById(long id);

	boolean disable(long id);

	UserDTO update(PSUser user) throws UserException;

	PSUser findByFirstName(String fistName);

	
}
