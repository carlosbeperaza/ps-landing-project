package com.ps.landing.project.servicesImpls;


import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ps.landing.project.dto.UserModuleDTO;
import com.ps.landing.project.repos.UserModuleRepo;
import com.ps.landing.project.services.UserModuleService;


public class UserModuleServiceImpl implements UserModuleService, UserDetailsService {

	
	private Logger log = LoggerFactory.getLogger(UserModuleServiceImpl.class.getName());

	@Autowired
	private UserModuleRepo userRepo;
	
	@Override
	public UserModuleDTO findByUserModule(String username) {
		return null;

		//return userRepo.findByUserModule(username).orElse(null);
	}
	
	
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
		
		/*UserModuleDTO user = userRepo.findByUserModule(username).orElse(null);

		List<GrantedAuthority> authorities = user.getModules().stream()
				.map(module -> new SimpleGrantedAuthority(module.getName()))
				.peek(authority -> log.info("Module: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(user.getUsername(), user.getPassword(), user.isStatus(), true, true, true, authorities);
	
	*/
	}
}

	


