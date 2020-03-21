package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.models.SubModule;

public interface SubModuleService {
	
	List<SubModuleDTO> findAll();
    SubModuleDTO findById(Long id);
    SubModuleDTO save(SubModule subModule);
    Object update(SubModule subModule);
    boolean disable(Long id);

}
