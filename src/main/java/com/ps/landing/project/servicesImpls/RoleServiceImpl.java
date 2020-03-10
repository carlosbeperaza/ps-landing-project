package com.ps.landing.project.servicesImpls;



import com.ps.landing.project.converters.RoleConverter;
import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.repos.RoleRepo;
import com.ps.landing.project.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

	
	private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class.getName());
    
    @Autowired
    private RoleRepo repo;
    
    @Autowired
    private RoleConverter converter;
    
	@Override
	public List<RoleDTO> findAll() {
		List<Role> roles = new ArrayList<>();
        repo.findAll().forEach(roles::add);
        return converter.convertToDTO(roles);
	}

	 @Override
	 @Transactional(readOnly = true)
	public RoleDTO findById(long id) {
		 Role role = repo.findById(id).orElse(null);
	     return (role != null) ? converter.convertToDTO(role) : null;
	}

	@Override
	public RoleDTO save(Role role) {
		return converter.convertToDTO(repo.save(role));
	}

	@Override
	public RoleDTO update(Role role) {
		Role formerRole = repo.findById(role.getId()).orElse(null);
        if(formerRole != null) {

            if(role.getName() == null)
            	role.setName(formerRole.getName());
            if(role.getDescription() == null)
            	role.setDescription(formerRole.getDescription());
            role.setCreateDate(formerRole.getCreateDate());
            

            return converter.convertToDTO(repo.save(role));
        }
        return null;
	}

	@Override
	public boolean disable(long id) {
		Role role = repo.findById(id).orElse(null);
        if(role != null) {

            role.setStatus(false);
            repo.save(role);
            return true;
        }
		
		
		return false;
	}

   
}
