package com.ps.landing.project.servicesImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.landing.project.converters.SubModuleConverter;
import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.exceptions.SubModuleException;
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
	public SubModuleDTO save(SubModule subModule) throws SubModuleException {
		SubModule coincidence = repo.findByNameAndParent(
                subModule.getName(), subModule.getParent()
        ).orElse(null);
        if(coincidence == null)
            return converter.convertToDTO(repo.save(subModule));
        else throw new SubModuleException("there's already a sub module with this name and parent");
	}

	@Override
	public SubModuleDTO update(SubModule subModule) throws SubModuleException {
		SubModule formerSubModule = repo.findById(subModule.getId()).orElse(null);
        if(formerSubModule != null) {

            if(subModule.getName() == null)
            	subModule.setName(formerSubModule.getName());
            
            if(subModule.getDescription() == null)
            	subModule.setDescription(formerSubModule.getDescription());
            
            if(subModule.getParent() == null)
            	subModule.setParent(formerSubModule.getParent());
            
            if(subModule.getUrl() == null)
            	subModule.setUrl(formerSubModule.getUrl());
            
            if(subModule.getIcon() == null)
            	subModule.setIcon(formerSubModule.getIcon());
            
            subModule.setStatus(formerSubModule.isStatus());
            subModule.setCreateDate(formerSubModule.getCreateDate());

            SubModule coincidence = repo.findByNameAndIdNotAndParent(
                    subModule.getName(), subModule.getId(), subModule.getParent()
            ).orElse(null);
            if(coincidence == null)
                return converter.convertToDTO(repo.save(subModule));
            else throw new SubModuleException("The parent module already have a sub module with this name");
        } else throw new SubModuleException("No sub module with given id");
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
