package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.models.User;
import com.ps.landing.project.models.Usuario;

public interface UserService {

	// Rest
		//public List<Usuario> findAll();
		 User save();
		 User findById(Long id);
		 void delete(Long id);
		 User modify(Long id);
		 User findByFirstName(String fistName);
		
		// Generic Methods
//		public Usuario findByEmailAndPassword(String email, String password);
//		public Usuario findByEmail(String email);
		//public Usuario findByUsername(String username);
}
