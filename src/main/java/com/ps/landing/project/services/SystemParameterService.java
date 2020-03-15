package com.ps.landing.project.services;

import com.ps.landing.project.dto.SystemParameterDTO;
import com.ps.landing.project.models.SystemParameter;

import java.util.List;

public interface SystemParameterService {

    List<SystemParameterDTO> findAll();
    SystemParameterDTO findById(Long id);
    SystemParameterDTO save(SystemParameter systemParameter);
    SystemParameterDTO update(SystemParameter systemParameter);
    boolean disable(Long id);
}
