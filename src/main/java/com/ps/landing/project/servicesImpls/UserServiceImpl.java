package com.ps.landing.project.servicesImpls;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.ps.landing.project.models.PSUser;
import com.ps.landing.project.repos.UserRepo;
import com.ps.landing.project.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserRepo userRepo;

	@Override
	public PSUser save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PSUser findById(Long id) {
		Optional<PSUser> optionalUser = userRepo.findById(id);
		return optionalUser.orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public PSUser modify(Long id) {
		// TODO Auto-generated method stub
		return null;
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

		return new User(user.getUsername(), user.getPassword(), user.getStatus(), true, true, true,
				authorities);
	}
}
