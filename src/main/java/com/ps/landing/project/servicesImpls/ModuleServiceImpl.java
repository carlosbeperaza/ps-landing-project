package com.ps.landing.project.servicesImpls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.landing.project.models.Module;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.repos.ModuleRepo;
import com.ps.landing.project.repos.RoleRepo;
import com.ps.landing.project.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	
	private Logger log = LoggerFactory.getLogger(ModuleServiceImpl.class.getName());
    private ModuleRepo moduleRepo;

    @Autowired
    void setRoleRepo(ModuleRepo moduleRepo) {
        this.moduleRepo = moduleRepo;
    }
    
    @Override
    @Transactional(readOnly = true)
	public Module findById(long id) {
    	Optional<Module> optionalModule = moduleRepo.findById(id);
        return optionalModule.orElse(null);
	}

	@Override
	public Module save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module modify(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
