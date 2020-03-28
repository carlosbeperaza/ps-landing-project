package com.ps.landing.project.servicesImpls;



import com.ps.landing.project.converters.RoleConverter;
import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.repos.RoleRepo;
import com.ps.landing.project.services.RoleService;
import com.ps.landing.project.exceptions.RoleException;
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
	@Transactional
	public RoleDTO save(Role role) throws RoleException {
		Role formerRole = repo.findByName(role.getName()).orElse(null);
		
		if(formerRole == null) {
            return converter.convertToDTO(repo.save(role));
        }
        else
            throw new RoleException("This role name is already in use");
	}

	@Override
	@Transactional
	public RoleDTO update(Role role) throws RoleException {
		Role formerRole = repo.findById(role.getId()).orElse(null);
        if(formerRole != null) {

            if(role.getName() == null)
            	role.setName(formerRole.getName());
            if(role.getDescription() == null)
            	role.setDescription(formerRole.getDescription());
            
            role.setStatus(formerRole.isStatus());
            role.setCreateDate(formerRole.getCreateDate());
            
            Role formerRoleUpdate = repo.findByName(role.getName()).orElse(null);
            if(formerRoleUpdate == null)
            	return converter.convertToDTO(repo.save(role));
            else throw new RoleException("This role name is already in use");
        }else 
        	throw new RoleException("There's no role with given id");
        
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
