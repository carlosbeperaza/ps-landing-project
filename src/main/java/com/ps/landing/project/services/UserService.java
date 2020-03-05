package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.models.PSUser;

public interface UserService {

	// Rest
		//public List<Usuario> findAll();
		 PSUser save();
		 PSUser findById(Long id);
		 void delete(Long id);
		 PSUser modify(Long id);
		 PSUser findByFirstName(String fistName);
		
		// Generic Methods
//		public Usuario findByEmailAndPassword(String email, String password);
//		public Usuario findByEmail(String email);
		//public Usuario findByUsername(String username);
}
