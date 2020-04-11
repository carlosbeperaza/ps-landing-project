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
	 * @param targetUser Usuario objetivo, solo se necesita el nombre de usuario y el correo electrónico.
	 * @return Respuesta con la información del estado de esta petición.
	 * */
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPass(@RequestBody PSUser targetUser) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;

		try {

			service.forgotPass(targetUser);
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
	 * @param id cadena en base 64 que corresponde al id del usuario a restablecer la contraseña
	 * @param newPass cadena en base 64 que corresponde la nueva contraseña
	 * @param formerPass Contraseña antigua codificada en base 64.
	 * @return Respuesta con la información del estado de esta petición.
	 * */
	@PutMapping("/password-reset/{id}/{newPass}/{formerPass}")
	public ResponseEntity<?> resetUserPass(
			@PathVariable String id,
			@PathVariable String newPass,
			@PathVariable String formerPass
	) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;

		try{

			newPass = passwordEncoder.encode(new String(Base64.getDecoder().decode(newPass)));
			UserDTO updatedUser = service.resetPass(id, newPass, formerPass);

			response.put("message", "Contraseña modificada exitosamente");
			response.put("updatedUser", updatedUser);
			responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (UserException e) {

			response.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {

			response.put("message", e.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	

	/**
	 * Método que recibe la petición de envío de correo de confirmación para restablecer una contraseña.
	 * @param targetUser Usuario objetivo, solo se necesita el nombre de usuario y el correo electrónico.
	 * @return Respuesta con la información del estado de esta petición.
	 * */
	@PostMapping("/email-password")
	public ResponseEntity<?> emailPass(@RequestBody PSUser targetUser) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> responseEntity;

		try {

			service.emailPass(targetUser);
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
