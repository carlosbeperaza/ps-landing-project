package com.ps.landing.project.controllers;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.landing.project.converters.UserConverter;
import com.ps.landing.project.dto.SidebarDTO;
import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService service;
	
	@Autowired
	private UserConverter userConverter;

	@Autowired
	void setService(UserService service) {
		this.service = service;
	}
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public ResponseEntity<?> getAllUser() {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;
		try {

			List<UserDTO> userDTOList = service.findAll();
			if (!userDTOList.isEmpty()) {

				response.put("Success", userDTOList);
			} else {

				response.put("Success", "list is empty");
			}
			responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {

			response.put("Error", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		UserDTO userDto = service.findById(id);

		if (userDto == null) {
			response.put("message", "Usuario No Existente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response.put("data", userDto);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);

		// return userService.getUsers();
	}

	@PostMapping("/add")
	@ResponseBody
//	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> addUser(@RequestBody UserDTO user) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;
		try {
			String passwordBcrypt = passwordEncoder.encode(user.getPassword());
			//user.setPassword(passwordBcrypt);
			UserDTO UserDTO = service.save(userConverter.UserDTOtoUser(user), passwordBcrypt);
			response.put("Success", UserDTO);
			responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			
		} catch(UserException e) {
			response.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {

			response.put("Error", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody PSUser user) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;
		try {
			//String passwordBcrypt = passwordEncoder.encode(user.getPassword());
			UserDTO updatedUser = service.update(user);
			//if (updatedUser != null) {

				response.put("Success", updatedUser);
				responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			//} else {
		} catch(UserException e) {
				response.put("message", e.getMessage());
				responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			//}
		} catch (Exception e) {

			response.put("Error", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> disableUser(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;
		try {

			if (service.disable(id)) {

				response.put("Success", "User disabled");
				responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			} else {

				response.put("message", "No user with given id");
				responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {

			response.put("Error message", e.getMessage());
			//response.put("Stack trace", e.getStackTrace());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping("sidebar/{id}")
	public ResponseEntity<?> getSideBar(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		List<SidebarDTO> sidebar = service.getSidebarByUserId(id);

		if (sidebar == null) {
			response.put("message", "No se pudo obtener el menú de navegación");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response.put("data", sidebar);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

}
