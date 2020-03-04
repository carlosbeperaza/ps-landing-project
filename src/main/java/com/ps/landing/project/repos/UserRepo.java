package com.ps.landing.project.repos;

import org.springframework.data.repository.CrudRepository;

import com.ps.landing.project.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	

}
