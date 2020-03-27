package com.ps.landing.project.controllers;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.services.UserService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService service;

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
	public ResponseEntity<?> addUser(@RequestBody PSUser user) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;
		try {
			String passwordBcrypt = passwordEncoder.encode(user.getPassword());
			//user.setPassword(passwordBcrypt);
			UserDTO UserDTO = service.save(user,passwordBcrypt);
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

	/**
	 * Método que recibe la petición de envío de correo de confirmación para restablecer una contraseña.
	 * @param username nombre de usuario en base 64.
	 * @param email correo del usuario en base 64.
	 * @return Respuesta con la información del estado de esta petición.
	 * */
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPass(
			@RequestHeader("username") String username,
			@RequestHeader("email") String email
	) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;

		try {

			service.forgotPass(
					/*new String(Base64.getDecoder().decode(username)),
					new String(Base64.getDecoder().decode(email))*/
					username, email);
			response.put("message", "Please wait for confirmation email");
			responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (UserException e) {

			response.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {

			response.put("message", e.getStackTrace());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	/**
	 * Método que recibe la petición de restablecer una contraseña.
	 * @param id id del usuario en base 64.
	 * @param pass nueva contraseña en base 64.
	 * @param securityString Contraseña antigua codificada en base 64.
	 * @return Respuesta con la información del estado de esta petición.
	 * */
	@PutMapping("/password-reset")
	public ResponseEntity<?> resetUserPass(
			@RequestHeader("id") String id,
			@RequestHeader("pass") String pass,
			@RequestHeader("security-string") String securityString
	) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;

		try{

			Long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
			String userPass = passwordEncoder.encode(new String(Base64.getDecoder().decode(pass.getBytes())));
			securityString = new String(Base64.getDecoder().decode(securityString));
			PSUser user = new PSUser();

			user.setId(userId);
			user.setPassword(userPass);
			UserDTO updatedUser = service.resetPass(user, securityString);

			response.put("message", updatedUser);
			responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (UserException e) {

			response.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {

			response.put("message", e.getStackTrace());
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

}
