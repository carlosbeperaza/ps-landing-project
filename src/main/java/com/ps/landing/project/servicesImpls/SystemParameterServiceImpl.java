package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.SystemParameterConverter;
import com.ps.landing.project.dto.SystemParameterDTO;
import com.ps.landing.project.models.SystemParameter;
import com.ps.landing.project.repos.SystemParameterRepo;
import com.ps.landing.project.services.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    private SystemParameterRepo repo;
    private SystemParameterConverter converter;

    @Autowired
    public SystemParameterServiceImpl(SystemParameterRepo repo, SystemParameterConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemParameterDTO> findAll() {

        List<SystemParameter> systemParameters = new ArrayList<>();
        repo.findAll().forEach(systemParameters::add);

        return converter.convertToDTO(systemParameters);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemParameterDTO findById(Long id) {

        SystemParameter systemParameter = repo.findById(id).orElse(null);

        return (systemParameter != null) ? converter.convertToDTO(systemParameter) : null;
    }

    @Override
    @Transactional
    public SystemParameterDTO save(SystemParameter systemParameter) {
        return converter.convertToDTO(repo.save(systemParameter));
    }

    @Override
    @Transactional
    public SystemParameterDTO update(SystemParameter systemParameter) {

        SystemParameter formerSystemParameter = repo.findById(systemParameter.getId()).orElse(null);
        if(formerSystemParameter != null) {

            if(systemParameter.getName() == null)
                systemParameter.setName(formerSystemParameter.getName());
            if(systemParameter.getValue() == null)
                systemParameter.setValue(formerSystemParameter.getValue());
            if(systemParameter.getDescription() == null)
                systemParameter.setDescription(formerSystemParameter.getDescription());
            systemParameter.setStatus(formerSystemParameter.isStatus());
            systemParameter.setCreateDate(formerSystemParameter.getCreateDate());
            systemParameter.setLastUpdateDate(new Date());

            return converter.convertToDTO(repo.save(systemParameter));
        }
        return null;
    }

    @Override
    @Transactional
    public boolean disable(Long id) {

        SystemParameter systemParameter = repo.findById(id).orElse(null);
        if(systemParameter != null) {

            systemParameter.setStatus(false);
            repo.save(systemParameter);
            return true;
        }
        return false;
    }
}
