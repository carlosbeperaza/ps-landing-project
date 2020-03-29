package com.ps.landing.project.repos;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.dto.UserDataDTO;



public interface UserDataRepo extends CrudRepository<UserDataDTO, Long>{
	
	Optional<UserDataDTO> findByUsername(String username);

}
