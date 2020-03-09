package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public ModuleDTO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO save(Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO update(Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean disable(long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*

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
	        return converter.convertToDTO(repo.save(module));
	    }

	    @Override
	    public ModuleDTO update(Module module) {

	        if(repo.findById(module.getId()).isPresent()) {

	            return converter.convertToDTO(repo.save(module));
	        }
	        return null;
	    }

	    @Override
	    public boolean disable(long id) {

	        Module module = repo.findById(id).orElse(null);
	        if(module != null) {

	            
	        }
	        return false;
	    }*/

}
