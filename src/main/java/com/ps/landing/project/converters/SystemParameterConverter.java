package com.ps.landing.project.converters;

import com.ps.landing.project.dto.SystemParameterDTO;
import com.ps.landing.project.models.SystemParameter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemParameterConverter {

    public SystemParameter convertToModel(SystemParameterDTO dto) {

        SystemParameter systemParameter = new SystemParameter();

        systemParameter.setId(dto.getId());
        systemParameter.setName(dto.getName());
        systemParameter.setValue(dto.getValue());
        systemParameter.setDescription(dto.getDescription());
        systemParameter.setStatus(dto.isStatus());
        systemParameter.setCreateDate(dto.getCreateDate());
        systemParameter.setLastUpdateDate(dto.getLastUpdateDate());

        return systemParameter;
    }

    public List<SystemParameter> convertToModel(List<SystemParameterDTO> dtoList) {

        List<SystemParameter> systemParameters = new ArrayList<>();
        for(SystemParameterDTO dto : dtoList) {
            systemParameters.add(convertToModel(dto));
        }

        return systemParameters;
    }

    public SystemParameterDTO convertToDTO(SystemParameter model) {

        SystemParameterDTO systemParameterDTO = new SystemParameterDTO();

        systemParameterDTO.setId(model.getId());
        systemParameterDTO.setName(model.getName());
        systemParameterDTO.setValue(model.getValue());
        systemParameterDTO.setDescription(model.getDescription());
        systemParameterDTO.setStatus(model.isStatus());
        systemParameterDTO.setCreateDate(model.getCreateDate());
        systemParameterDTO.setLastUpdateDate(model.getLastUpdateDate());

        return systemParameterDTO;
    }

    public List<SystemParameterDTO> convertToDTO(List<SystemParameter> models) {

        List<SystemParameterDTO> systemParameterDTOS = new ArrayList<>();
        for(SystemParameter model : models) {
            systemParameterDTOS.add(convertToDTO(model));
        }

        return systemParameterDTOS;
    }
}
