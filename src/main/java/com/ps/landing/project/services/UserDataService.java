package com.ps.landing.project.services;

import com.ps.landing.project.dto.UserDataDTO;

public interface UserDataService {
	UserDataDTO findByUsername(String username);

}
