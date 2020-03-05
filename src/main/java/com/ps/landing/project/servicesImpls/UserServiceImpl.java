package com.ps.landing.project.servicesImpls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ps.landing.project.models.User;
import com.ps.landing.project.repos.UserRepo;
import com.ps.landing.project.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());
	private UserRepo repo;

	@Autowired
	void setRepo(UserRepo repo) {
		this.repo = repo;
	}
	    
	@Override
	public User save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalUser = repo.findById(id);
        return optionalUser.orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User modify(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByFirstName(String firstName) {
		return repo.findByName(firstName);
	}

}
