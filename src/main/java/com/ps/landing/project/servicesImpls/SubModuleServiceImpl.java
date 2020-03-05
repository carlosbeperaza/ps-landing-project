package com.ps.landing.project.servicesImpls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ps.landing.project.models.SubModule;
import com.ps.landing.project.repos.SubModuleRepo;
import com.ps.landing.project.services.SubModuleService;

public class SubModuleServiceImpl implements SubModuleService{
	
	private Logger log = LoggerFactory.getLogger(SubModuleServiceImpl.class.getName());
	
	@Autowired
    private SubModuleRepo submoduleRepo;

    @Override
    @Transactional(readOnly = true)
	public SubModule findById(long id) {
    	Optional<SubModule> optionalSubModule = submoduleRepo.findById(id);
        return optionalSubModule.orElse(null);
	}

	@Override
	public SubModule save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubModule modify(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
