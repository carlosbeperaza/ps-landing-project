package com.ps.landing.project.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import com.ps.landing.project.models.Module;
import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.models.SubModule;


@Component("ModuleConverter")
public class ModuleConverter {
	
	public Module convertToModel(ModuleDTO dto) {
		
		

        Module module = new Module();
        List<SubModuleDTO> subModuleDTOS = dto.getSubModules();
        List<SubModule> subModules = new ArrayList<>();

        module.setId(dto.getId());
        module.setName(dto.getName());
        module.setDescription(dto.getDescription());
        module.setStatus(dto.isStatus());
        module.setCreateDate((Timestamp) dto.getCreateDate());
        module.setLastUpdateDate((Timestamp) dto.getLastUpdateDate());

        if(subModuleDTOS != null) {

            for(SubModuleDTO subModuleDTO : subModuleDTOS) {

                SubModule subModule = new SubModule();

                subModule.setId(subModuleDTO.getId());
                subModule.setName(subModuleDTO.getName());
                subModule.setDescription(subModuleDTO.getDescription());
                subModule.setParent(dto.getId());
                subModule.setStatus(subModuleDTO.isStatus());
                subModule.setCreateDate((Timestamp) subModuleDTO.getCreateDate());
                subModule.setLastUpdateDate((Timestamp) subModuleDTO.getLastUpdateDate());

                subModules.add(subModule);
            }
        }
        module.setSubModules(subModules);

        return module;
    }
	
	public List<Module> convertToModel(List<ModuleDTO> dtoList) {

        List<Module> modules = new ArrayList<>();

        for(ModuleDTO moduleDTO : dtoList) {

            Module module = convertToModel(moduleDTO);
            modules.add(module);
        }

        return modules;
    }
	
	public ModuleDTO convertToDTO(Module model) {

        ModuleDTO moduleDTO = new ModuleDTO();
        List<SubModule> subModules = model.getSubModules();
        List<SubModuleDTO> subModuleDTOS = new ArrayList<>();

        moduleDTO.setId(model.getId());
        moduleDTO.setName(model.getName());
        moduleDTO.setDescription(model.getDescription());
        moduleDTO.setStatus(model.isStatus());
        moduleDTO.setCreateDate(model.getCreateDate());
        moduleDTO.setLastUpdateDate(model.getLastUpdateDate());

        if(subModules != null) {

            for(SubModule subModule : subModules) {

                SubModuleDTO subModuleDTO = new SubModuleDTO();

                subModuleDTO.setId(subModule.getId());
                subModuleDTO.setName(subModule.getName());
                subModuleDTO.setDescription(subModule.getDescription());
                subModuleDTO.setStatus(subModule.isStatus());
                subModuleDTO.setCreateDate(subModule.getCreateDate());
                subModuleDTO.setLastUpdateDate(subModule.getLastUpdateDate());

                subModuleDTOS.add(subModuleDTO);
            }
        }
        moduleDTO.setSubModules(subModuleDTOS);

        return moduleDTO;
    }
	 
	
	public List<ModuleDTO> convertToDTO(List<Module> modules) {

        List<ModuleDTO> moduleDTOS = new ArrayList<>();

        for(Module module : modules) {

            ModuleDTO moduleDTO = convertToDTO(module);
            moduleDTOS.add(moduleDTO);
        }

        return moduleDTOS;
    }

}
