package com.ps.landing.project.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.SubCatalog;


@Component("ModuleConverter")
public class ModuleConverter {
	
	public Module convertToModel(ModuleDTO dto) {

        Module module = new Catalog();
        List<SubCatalogDTO> subCatalogDTOS = dto.getSubCatalogs();
        List<SubCatalog> subCatalogs = new ArrayList<>();

        module.setId(dto.getId());
        module.setName(dto.getName());
        module.setDescription(dto.getDescription());
        module.setStatus(dto.isStatus());
        module.setCreateDate((Timestamp) dto.getCreateDate());
        module.setLastUpdateDate((Timestamp) dto.getLastUpdateDate());

        if(subCatalogDTOS != null) {

            for(SubCatalogDTO subCatalogDTO : subCatalogDTOS) {

                SubCatalog subCatalog = new SubCatalog();

                subCatalog.setId(subCatalogDTO.getId());
                subCatalog.setName(subCatalogDTO.getName());
                subCatalog.setDescription(subCatalogDTO.getDescription());
                subCatalog.setParent(dto.getId());
                subCatalog.setStatus(subCatalogDTO.isStatus());
                subCatalog.setCreateDate((Timestamp) subCatalogDTO.getCreateDate());
                subCatalog.setLastUpdateDate((Timestamp) subCatalogDTO.getLastUpdateDate());

                subCatalogs.add(subCatalog);
            }
        }
        catalog.setSubCatalogs(subCatalogs);

        return catalog;
    }

}
