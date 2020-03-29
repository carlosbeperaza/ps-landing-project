package com.ps.landing.project.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.SidebarDTO;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.models.SubModule;

@Component("SidebarConverter")
public class SidebarConverter {
	
	public List<SidebarDTO> convertToSidebar(List<Module> modules) {

		List<SidebarDTO> sidebar = new ArrayList<>();

		for (Module module : modules) {

			SidebarDTO moduleDTO = convertToSidebar(module);
			sidebar.add(moduleDTO);
		}

		return sidebar;
	}
	
	public SidebarDTO convertToSidebar(Module model) {

		SidebarDTO sidebar = new SidebarDTO();
		
		sidebar.setId(model.getId());
		sidebar.setName(model.getName());
		sidebar.setUrl(model.getUrl());
		sidebar.setIcon(model.getIcon());
		
		sidebar.setChildren(model.getSubModules().isEmpty() ? null : childToSidebar(model.getSubModules()));

		return sidebar;
	}
	
	public List<SidebarDTO> childToSidebar(List<SubModule> subModules) {

		List<SidebarDTO> sidebar = new ArrayList<>();

		for (SubModule module : subModules) {

			SidebarDTO moduleDTO = childToSidebar(module);
			sidebar.add(moduleDTO);
		}

		return sidebar;
	}
	
	public SidebarDTO childToSidebar(SubModule model) {

		SidebarDTO sidebar = new SidebarDTO();
		
		sidebar.setId(model.getId());
		sidebar.setName(model.getName());
		sidebar.setUrl(model.getUrl());
		sidebar.setIcon(model.getIcon());
		
		return sidebar;
	}

}
