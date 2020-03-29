package com.ps.landing.project.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;

import com.ps.landing.project.converters.UserDataConverter;
import com.ps.landing.project.dto.UserDataDTO;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.repos.UserRepo;
import com.ps.landing.project.services.UserDataService;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserDataConverter userconverter;
	@Override
	public UserDataDTO findByUsername(String username) {
		
		 PSUser user = userRepo.findByUsername(username).orElse(null);

		 return (user != null) ? userconverter.UsertoUserDataDTO(user) : null;
	}
	
	
	
}

	


