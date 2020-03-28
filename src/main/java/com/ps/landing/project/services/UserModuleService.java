package com.ps.landing.project.services;

import com.ps.landing.project.dto.UserModuleDTO;

public interface UserModuleService {
	UserModuleDTO findByUserModule(String username);

}
