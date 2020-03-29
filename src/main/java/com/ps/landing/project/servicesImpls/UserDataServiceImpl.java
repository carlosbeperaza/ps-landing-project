package com.ps.landing.project.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;

import com.ps.landing.project.converters.UserDataConverter;
import com.ps.landing.project.dto.UserDataDTO;
import com.ps.landing.project.repos.UserDataRepo;
import com.ps.landing.project.services.UserDataService;


public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataRepo userRepo;

	@Autowired
	private UserDataConverter userconverter;
	@Override
	public UserDataDTO findByUsername(String username) {
		
		UserDataDTO user = userRepo.findByUsername(username).orElse(null);

		return (user != null) ? userconverter.UsertoUserDataDTO(user) : null;
	}
	
	
	
}

	


