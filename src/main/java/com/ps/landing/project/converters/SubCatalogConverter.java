package com.ps.landing.project.converters;

import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.SubCatalog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubCatalogConverter {

    public SubCatalog convertToModel(SubCatalogDTO dto) {

        SubCatalog subCatalog = new SubCatalog();

        subCatalog.setId(dto.getId());
        subCatalog.setName(dto.getName());
        subCatalog.setDescription(dto.getDescription());
        subCatalog.setParent(dto.getParent());
        subCatalog.setStatus(dto.isStatus());
        subCatalog.setCreateDate(dto.getCreateDate());
        subCatalog.setLastUpdateDate(dto.getLastUpdateDate());

        return subCatalog;
    }

    public List<SubCatalog> convertToModel(List<SubCatalogDTO> dtoList) {

        List<SubCatalog> catalogs = new ArrayList<>();

        for(SubCatalogDTO dto : dtoList) {

            catalogs.add(convertToModel(dto));
        }

        return catalogs;
    }

    public SubCatalogDTO convertToDTO(SubCatalog model) {

        SubCatalogDTO subCatalogDTO = new SubCatalogDTO();

        subCatalogDTO.setId(model.getId());
        subCatalogDTO.setName(model.getName());
        subCatalogDTO.setDescription(model.getDescription());
        subCatalogDTO.setParent(model.getParent());
        subCatalogDTO.setStatus(model.isStatus());
        subCatalogDTO.setCreateDate(model.getCreateDate());
        subCatalogDTO.setLastUpdateDate(model.getLastUpdateDate());

        return subCatalogDTO;
    }

    public List<SubCatalogDTO> convertToDTO(List<SubCatalog> models) {

        List<SubCatalogDTO> catalogDTOS = new ArrayList<>();

        for(SubCatalog model : models) {

            catalogDTOS.add(convertToDTO(model));
        }

        return catalogDTOS;
    }
}
