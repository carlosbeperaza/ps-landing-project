package com.ps.landing.project.services;

import com.ps.landing.project.dto.SystemParameterDTO;
import com.ps.landing.project.exceptions.SystemParameterException;
import com.ps.landing.project.models.SystemParameter;

import java.util.List;

public interface SystemParameterService {

    List<SystemParameterDTO> findAll();
    SystemParameterDTO findById(Long id);
    SystemParameterDTO save(SystemParameter systemParameter) throws SystemParameterException;
    SystemParameterDTO update(SystemParameter systemParameter) throws SystemParameterException;
    boolean disable(Long id);
}
