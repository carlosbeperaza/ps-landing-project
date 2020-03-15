package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.models.Module;


public interface ModuleService {

	List<ModuleDTO> findAll();
    ModuleDTO findById(long id);
    ModuleDTO save(Module module);
    ModuleDTO update(Module module);

    boolean disable(long id);
}
