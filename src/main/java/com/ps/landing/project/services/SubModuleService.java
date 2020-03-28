package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.exceptions.SubModuleException;
import com.ps.landing.project.models.SubModule;

public interface SubModuleService {
	
	List<SubModuleDTO> findAll();
    SubModuleDTO findById(Long id);
    SubModuleDTO save(SubModule subModule) throws SubModuleException;
    SubModuleDTO update(SubModule subModule) throws SubModuleException;
    boolean disable(Long id);

}
