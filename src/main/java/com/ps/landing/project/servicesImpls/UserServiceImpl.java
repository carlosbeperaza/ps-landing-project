package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.ps.landing.project.exceptions.CatalogException;
import com.ps.landing.project.exceptions.UserException;
import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.repos.UserRepo;
import com.ps.landing.project.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserConverter userconverter;
	
	@Autowired
	public EmailServiceImpl Gmail;
	
	/*@Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

	@Override
	public List<UserDTO> findAll() {

		List<PSUser> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		//Gmail.sendSimpleMessage("brianreach117@hotmail.com", "Prueba", "yase");

		return userconverter.convertToDTO(users);
	}

	@Override
	@Transactional
	public UserDTO save(PSUser user, String passwordBcrypt) throws UserException{
		//String username;
		PSUser formerUser = userRepo.findFirstByUsernameOrEmail(user.getUsername(),user.getEmail()).orElse(null);
		if(formerUser == null) {
			//String a =user.setEmail(formerUser.getEmail());
			//String passwordBcrypt = passwordEncoder.encode(user.getPassword());
			user.setPassword(passwordBcrypt);
			Gmail.sendSimpleMessage(user.getEmail(), "Bienvenido "+ user.getName(), "Hola "+ user.getName() +" " + user.getLastname()+", te haz registrado exitosamente uWu");
			return userconverter.UsertoUserDTO(userRepo.save(user));
		}
		else throw new UserException("This username is already in use");
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(long id) {
		PSUser user = userRepo.findById(id).orElse(null);

		return (user != null) ? userconverter.UsertoUserDTO(user) : null;
	}
	

	@Override
	@Transactional
	public UserDTO update(PSUser user) throws UserException {
		PSUser formerUser = userRepo.findById(user.getId()).orElse(null);
        if(formerUser != null) {

        	System.out.println("Entro1");
        	if(user.getName() == null)
            	user.setName(formerUser.getName());
            if(user.getLastname() == null)
            	user.setLastname(formerUser.getLastname());
            if(user.getEmail() == null)
            	user.setEmail(formerUser.getEmail());
            if(user.getPassword() == null) 
            	user.setPassword(formerUser.getPassword());
           
            user.setStatus(formerUser.isStatus());
            user.setRegistrationDate(formerUser.getRegistrationDate());
            user.setUpdateDate(new Date());
            user.setUsername(formerUser.getUsername());
            
            
            PSUser coincidence = userRepo.b(user.getUsername(),user.getEmail(),user.getId()).orElse(null);
            
            /*PSUser coincidence = userRepo.findByUsernameAndIdNot(user.getUsername(),user.getId()).orElse(null);
            PSUser coincidence2 = userRepo.findByEmailAndIdNot(user.getEmail(),user.getId()).orElse(null);*/
            if(coincidence == null)
            {
            	
            		System.out.println("Entro2");
            		return userconverter.UsertoUserDTO(userRepo.save(user));
               
            	
            }
            else throw new UserException("This Username or email is already in use");
        }
        else throw new UserException("There's no user with given id");
	}
	
	@Override
	public boolean disable(long id) {
		 PSUser user = userRepo.findById(id).orElse(null);
	        if(user != null) {

	        	user.setStatus(false);
	        	userRepo.save(user);
	            return true;
	        }
	        return false;
	    }

	@Override
	public PSUser findByFirstName(String firstName) {
		return userRepo.findByName(firstName);
	}

	// Generic Methods

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		PSUser user = userRepo.findByUsername(username);

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
