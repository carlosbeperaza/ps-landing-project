package com.ps.landing.project.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.models.SubModule;

@Component
public class SubModuleConverter {
	
	public SubModule convertToModel(SubModuleDTO dto) {

        SubModule subModule = new SubModule();

        subModule.setId(dto.getId());
        subModule.setName(dto.getName());
        subModule.setDescription(dto.getDescription());
        subModule.setParent(dto.getParent());
        subModule.setUrl(dto.getUrl());
        subModule.setIcon(dto.getIcon());
        subModule.setStatus(dto.isStatus());
        subModule.setCreateDate(dto.getCreateDate());
        subModule.setLastUpdateDate(dto.getLastUpdateDate());

        return subModule;
    }
	public List<SubModule> convertToModel(List<SubModuleDTO> dtoList) {

        List<SubModule> modules = new ArrayList<>();

        for(SubModuleDTO dto : dtoList) {

            modules.add(convertToModel(dto));
        }

        return modules;
    }
	
	public SubModuleDTO convertToDTO(SubModule model) {

        SubModuleDTO subModuleDTO = new SubModuleDTO();

        subModuleDTO.setId(model.getId());
        subModuleDTO.setName(model.getName());
        subModuleDTO.setDescription(model.getDescription());
        subModuleDTO.setParent(model.getParent());
        subModuleDTO.setUrl(model.getUrl());
        subModuleDTO.setIcon(model.getIcon());
        subModuleDTO.setStatus(model.isStatus());
        //subModuleDTO.setCreateDate(model.getCreateDate());
        //subModuleDTO.setLastUpdateDate(model.getLastUpdateDate());

        return subModuleDTO;
    }
	public List<SubModuleDTO> convertToDTO(List<SubModule> models) {

        List<SubModuleDTO> moduleDTOS = new ArrayList<>();

        for(SubModule model : models) {

            moduleDTOS.add(convertToDTO(model));
        }

        return moduleDTOS;
    }

}
