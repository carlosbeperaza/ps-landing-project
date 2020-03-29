package com.ps.landing.project.services;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.UserDataDTO;

@Component
public interface UserDataService {
	UserDataDTO findByUsername(String username);

}
