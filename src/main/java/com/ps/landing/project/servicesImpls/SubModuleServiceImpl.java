package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.landing.project.converters.SubModuleConverter;
import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.models.SubModule;
import com.ps.landing.project.repos.SubModuleRepo;
import com.ps.landing.project.services.SubModuleService;

@Service
public class SubModuleServiceImpl implements SubModuleService{
	
	private SubModuleRepo repo;
    private SubModuleConverter converter;

    @Autowired
    public SubModuleServiceImpl(SubModuleRepo repo, SubModuleConverter converter) {
        this.repo = repo;
        this.converter = converter;
       
    }

        @Override
        @Transactional
	public List<SubModuleDTO> findAll() {
        	List<SubModule> subModules = new ArrayList<>();
            repo.findAll().forEach(subModules::add);

            return converter.convertToDTO(subModules);
	}

        @Override
        @Transactional
	public SubModuleDTO findById(Long id) {
        	SubModule subModule = repo.findById(id).orElse(null);

            return (subModule != null) ? converter.convertToDTO(subModule) : null;
	}

	@Override
	public SubModuleDTO save(SubModule subModule) {
		return converter.convertToDTO(repo.save(subModule));
	}

	@Override
	public SubModuleDTO update(SubModule subModule) {
		if(repo.findById(subModule.getId()).isPresent()) {

            return converter.convertToDTO(repo.save(subModule));
        }
        return null;
	}

	@Override
	public boolean disable(Long id) {
		SubModule subModule = repo.findById(id).orElse(null);
        if(subModule != null) {

            subModule.setStatus(false);
            repo.save(subModule);
            return true;
        }
        return false;
	}
	
	

}
