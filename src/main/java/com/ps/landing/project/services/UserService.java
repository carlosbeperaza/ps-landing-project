package com.ps.landing.project.services;

import java.util.List;


import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;

public interface UserService {

	List<UserDTO> findAll();

	UserDTO save(PSUser user) throws UserException;

	UserDTO findById(long id);

	boolean disable(long id);

	UserDTO update(PSUser user) throws UserException;

	PSUser findByUsername(String username);

	void forgotPass(PSUser targetUser) throws UserException;

	UserDTO resetPass(String id, String newPass, String formerPass) throws UserException;
	
	void emailPass(PSUser targetUser) throws UserException;
}
