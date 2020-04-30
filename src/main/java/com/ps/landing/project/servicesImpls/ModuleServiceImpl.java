package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ps.landing.project.converters.ModuleConverter;
import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.exceptions.ModuleException;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.models.SubModule;
import com.ps.landing.project.repos.ModuleRepo;
import com.ps.landing.project.repos.SubModuleRepo;
import com.ps.landing.project.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	private Logger log = LoggerFactory.getLogger(ModuleServiceImpl.class.getName());
    private ModuleRepo repo;
    private SubModuleRepo subRepo;
    private ModuleConverter converter;
    
    @Autowired
    public ModuleServiceImpl(ModuleRepo repo, SubModuleRepo subRepo,ModuleConverter converter) {
        this.repo = repo;
        this.subRepo = subRepo;
        this.converter = converter;
    }

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
	 @Transactional
	public ModuleDTO save(Module module) throws ModuleException {
		Module coincidence = repo.findByName(module.getName()).orElse(null);
        if(coincidence == null) {
            
        	List<SubModule> subModules = module.getSubModules();
            module.setSubModules(new ArrayList<>());
            Module newModule = repo.save(module);
            List<SubModule> newSubModules = new ArrayList<>();
            for(SubModule subModule: subModules) {

                subModule.setParent(newModule.getId());
                SubModule subCoincidence = subRepo.findByNameAndParent(
                        subModule.getName(), subModule.getParent()
                ).orElse(null);
                if(subCoincidence == null) {

                    subRepo.save(subModule);
                    newSubModules.add(subModule);
                }
            }
            newModule.setSubModules(newSubModules);
            return converter.convertToDTO(newModule);
        }
        else throw new ModuleException("This module name is already in use");
	}

	@Override
	public ModuleDTO update(Module module) throws ModuleException{
		
		 Module formerModule = repo.findById(module.getId()).orElse(null);
	        if(formerModule != null) {

	            /*if(module.getName() == null)
	                module.setName(formerModule.getName());
	            
	            if(module.getDescription() == null)
	                module.setDescription(formerModule.getDescription());
	            
	            if(module.getUrl() == null)
	                module.setUrl(formerModule.getUrl());
	            
	            if(module.getIcon() == null)
	                module.setIcon(formerModule.getIcon());
	            
	            module.setStatus(formerModule.isStatus());
	            
	            if(module.getSubModules() == null || module.getSubModules().isEmpty())
	                module.setSubModules(formerModule.getSubModules());*/
	        	
	        	if(module.getName() == null) 			{ module.setName(formerModule.getName()); }	
	        	if(module.getDescription() == null) 			{ module.setDescription(formerModule.getDescription()); }
	        	if(module.getUrl() == null) 			{ module.setUrl(formerModule.getUrl()); }
	        	if(module.getIcon() == null) 			{ module.setIcon(formerModule.getIcon()); }
	        	if(module.getName() == null) 			{ module.setName(formerModule.getName()); }	
	        	if(module.getSubModules() == null) 		{ module.setSubModules(formerModule.getSubModules()); }
	        	if(!module.isStatus()) 				    { module.setStatus(formerModule.isStatus());}
	            
	        	module.setCreateDate(formerModule.getCreateDate());
	            
	            module.setLastUpdateDate(new Date());

	            Module coincidence = repo.findByNameAndIdNot(module.getName(), module.getId()).orElse(null);
	            if(coincidence == null)
	                return converter.convertToDTO(repo.save(module));
	            else throw new ModuleException("This module name is already in use");
	        } else throw new ModuleException("There's no module with given id");
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
