package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.ps.landing.project.converters.UserConverter;
import com.ps.landing.project.dto.UserDTO;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.repos.UserRepo;
import com.ps.landing.project.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());
	private final UserRepo repo;
	private final UserConverter converter;
	private final EmailServiceImpl gmail;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserServiceImpl(UserRepo repo, UserConverter converter, EmailServiceImpl gmail) {
		this.repo = repo;
		this.converter = converter;
		this.gmail = gmail;
	}

	@Override
	public List<UserDTO> findAll() {

		List<PSUser> users = new ArrayList<>();
		repo.findAll().forEach(users::add);
		//Gmail.sendSimpleMessage("brianreach117@hotmail.com", "Prueba", "Prueba");

		return converter.convertToDTO(users);
	}

	@Override
	@Transactional
	public UserDTO save(PSUser user) throws UserException{

		PSUser coincidenceByUserName = repo.findByUsername(user.getUsername()).orElse(null);
		PSUser coincidenceByEmail = repo.findByEmail(user.getEmail()).orElse(null);

		if(coincidenceByUserName == null) {

			if(coincidenceByEmail == null) {

				user.setPassword(passwordEncoder.encode(user.getPassword()));
				gmail.sendSimpleMessage(user.getEmail(), "Bienvenido "+ user.getName(), "Hola "+ user.getName() +" " + user.getLastname()+", te haz registrado exitosamente uWu");
				return converter.UsertoUserDTO(repo.save(user));
			} else throw new UserException("This email is already in use");
		} else throw new UserException("This username is already in use");
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(long id) {
		PSUser user = repo.findById(id).orElse(null);

		return (user != null) ? converter.UsertoUserDTO(user) : null;
	}
	

	@Override
	@Transactional
	public UserDTO update(PSUser user) throws UserException {
		PSUser formerUser = repo.findById(user.getId()).orElse(null);

        if(formerUser != null) {

        	if(user.getName() == null)
            	user.setName(formerUser.getName());
            if(user.getLastname() == null)
            	user.setLastname(formerUser.getLastname());
            if(user.getEmail() == null)
            	user.setEmail(formerUser.getEmail());
            if(user.getPassword() == null)
            	user.setPassword(formerUser.getPassword());
            if(user.getUsername() == null)
            	user.setUsername(formerUser.getUsername());
            if(user.getRoles() == null)
            	user.setRoles(formerUser.getRoles());
            user.setStatus(formerUser.isStatus());
            user.setRegistrationDate(formerUser.getRegistrationDate());
            user.setUpdateDate(new Date());

			List<PSUser> coincidences = new ArrayList<>();
			repo.findByUsernameOrEmail(user.getUsername(), user.getEmail()).forEach(coincidences::add);

			for(PSUser coincidence: coincidences) {

				if(!coincidence.getId().equals(user.getId())) {

					if(coincidence.getUsername().equals(user.getUsername()))
						throw new UserException("This username is already in use");
					if(coincidence.getEmail().equals(user.getEmail()))
						throw new UserException("This email is already in use");
				}
			}

			return converter.UsertoUserDTO(repo.save(user));
        } else throw new UserException("There's no user with given id");
	}
	
	@Override
	public boolean disable(long id) {
		 PSUser user = repo.findById(id).orElse(null);
	        if(user != null) {

	        	user.setStatus(false);
	        	repo.save(user);
	            return true;
	        }
	        return false;
	    }

	@Override
	public PSUser findByUsername(String username) {
		return repo.findByUsername(username).orElse(null);
	}

	/**
	 * Método responsable de enviar el correo de confirmación para restablecer una contraseña según el nombre de usuario
	 * y su correo electrónico, este método solo funcionara para los usuarios activos.
	 * @param targetUser Usuario objetivo, solo se necesita el nombre de usuario y el correo electrónico para realizar
	 *                   la validación.
	 * @throws UserException si no existe un usuario con el nombre de usuario y correo proporcionados o si este NO esta
	 * activo.
	 * */
	@Override
	public void forgotPass(PSUser targetUser) throws UserException {

		if ((targetUser.getUsername() == null || targetUser.getUsername().equals("")) ||
				(targetUser.getEmail() == null || targetUser.getEmail().equals(""))) {
			throw new UserException("Missing credentials");
		}
		String username = new String(Base64.getDecoder().decode(targetUser.getUsername()));
		String email = new String(Base64.getDecoder().decode(targetUser.getEmail()));
		PSUser user = repo.findByUsernameAndEmail(username, email).orElse(null);

		if (user != null) {

			if (user.isStatus()) {

				String userId = new String(Base64.getEncoder().encode((user.getId() + "").getBytes()))
						.replaceAll("=", "");
				String formerPass = new String(Base64.getEncoder().encode((user.getPassword()).getBytes()));
				gmail.sendSimpleMessage(
						email,
						"Confirmar restablecimiento de contraseña",
						"Se ha solicitado el restablecimiento de contraseña para la cuenta '"+username+"' asociada" +
								"a esta dirección de correo electrónico, si este es el caso por favor siga el siguiente " +
								"enlace: http://localhost:4200/#/change/newPassword/"+userId+"/"+formerPass+", de lo " +
								"contrario puede descartar este correo."
				);
			} else throw new UserException("This user is not active");
		} else throw new UserException("Invalid credentials");
	}

	@Override
	public UserDTO resetPass(String id, String newPass, String formerPass) throws UserException {

		Long targetId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
		PSUser coincidence = repo.findById(targetId).orElse(null);
		formerPass = new String(Base64.getDecoder().decode(formerPass));
		newPass = passwordEncoder.encode(new String(Base64.getDecoder().decode(newPass)));

		if(coincidence != null) {

			System.out.println(formerPass);
			System.out.println(coincidence.getPassword());
			if(coincidence.getPassword().equals(formerPass)) {
				coincidence.setPassword(newPass);
				return update(coincidence);
			} else throw new UserException("Operation aborted, security violation");
		} else throw new UserException("No user with given id");
	}

	// Generic Methods

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		PSUser user = repo.findByUsername(username).orElse(null);

		if (user == null) {
			log.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(user.getUsername(), user.getPassword(), user.isStatus(), true, true, true, authorities);
	}

	

}
