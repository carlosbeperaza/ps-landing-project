package com.ps.landing.project.repos;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.dto.UserModuleDTO;
import com.ps.landing.project.models.PSUser;


public interface UserModuleRepo extends CrudRepository<PSUser, Long>{
	
	//Optional<UserModuleDTO> findByUserModule(String username);

}
