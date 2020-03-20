package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.landing.project.converters.ModuleConverter;
import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.repos.ModuleRepo;
import com.ps.landing.project.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	private Logger log = LoggerFactory.getLogger(ModuleServiceImpl.class.getName());
    
    @Autowired
    private ModuleRepo repo;
    
    @Autowired
    private ModuleConverter converter;

	@Override
	public List<ModuleDTO> findAll() {
		List<Module> modules = new ArrayList<>();
        repo.findAll().forEach(modules::add);

        return converter.convertToDTO(modules);
	}

	 @Override
	 @Transactional(readOnly = true)
	public ModuleDTO findById(long id) {
		Module module = repo.findById(id).orElse(null);
        return (module != null) ? converter.convertToDTO(module) : null;
	}

	@Override
	public ModuleDTO save(Module module) {
		Module coincidence = repo.findByName(module.getName()).orElse(null);
		
		if(coincidence == null) {
			return converter.convertToDTO(repo.save(module));
		}else {
			return null;
		}
	
	}

	@Override
	public ModuleDTO update(Module module) {
		Module formerModule = repo.findById(module.getId()).orElse(null);
        if(formerModule != null) {

            if(module.getName() == null)
            	module.setName(formerModule.getName());
            
            if(module.getDescription() == null)
            	module.setDescription(formerModule.getDescription());
            
            if(module.getUrl() == null)
            	module.setUrl(formerModule.getUrl());
            
            if(module.getIcon() == null)
            	module.setIcon(formerModule.getIcon());
            
            module.setStatus(formerModule.isStatus());
            module.setCreateDate(formerModule.getCreateDate());
            
            if(module.getSubModules() == null || module.getSubModules().isEmpty())
                module.setSubModules(formerModule.getSubModules());
            

            return converter.convertToDTO(repo.save(module));
        }
        return null;
	}

	@Override
	public boolean disable(long id) {
	
		Module module = repo.findById(id).orElse(null);
        if(module != null) {

            module.setStatus(false);
            repo.save(module);
            return true;
        }
		
		
		return false;
	}
	
	
}
