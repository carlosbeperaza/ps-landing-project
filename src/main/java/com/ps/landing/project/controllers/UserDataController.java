package com.ps.landing.project.controllers;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ps.landing.project.dto.UserDataDTO;
import com.ps.landing.project.services.UserDataService;

@RestController
@RequestMapping("/userdata")
public class UserDataController {

	private UserDataService service;

	@Autowired
	void setService(UserDataService service) {
		this.service = service;
	}

	

	@GetMapping("/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable String username) {
		Map<String, Object> response = new HashMap<>();

		UserDataDTO userDto = service.findByUsername(username);

		if (userDto == null) {
			response.put("message", "Usuario No Existente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response.put("data", userDto);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);

		
	}


}
