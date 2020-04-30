package com.ps.landing.project.converters;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.SubCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("CatalogConverter")
public class CatalogConverter {

    private SubCatalogConverter subConverter;

    @Autowired
    public CatalogConverter(SubCatalogConverter subConverter) {
        this.subConverter = subConverter;
    }

    public Catalog convertToModel(CatalogDTO dto) {

        Catalog catalog = new Catalog();
        List<SubCatalogDTO> subCatalogDTOS = dto.getSubCatalogs();
        List<SubCatalog> subCatalogs = new ArrayList<>();

        catalog.setId(dto.getId());
        catalog.setName(dto.getName());
        catalog.setDescription(dto.getDescription());
        catalog.setStatus(dto.isStatus());
        catalog.setCreateDate(dto.getCreateDate());
        catalog.setLastUpdateDate(dto.getLastUpdateDate());

        if(subCatalogDTOS != null) {

            for(SubCatalogDTO subCatalogDTO : subCatalogDTOS) {

                SubCatalog subCatalog = subConverter.convertToModel(subCatalogDTO);
                subCatalogs.add(subCatalog);
            }
        }
        catalog.setSubCatalogs(subCatalogs);

        return catalog;
    }

    public List<Catalog> convertToModel(List<CatalogDTO> dtoList) {

        List<Catalog> catalogs = new ArrayList<>();
        for(CatalogDTO catalogDTO : dtoList) {
            catalogs.add(convertToModel(catalogDTO));
        }

        return catalogs;
    }

    public CatalogDTO convertToDTO(Catalog model) {

        CatalogDTO catalogDTO = new CatalogDTO();
        List<SubCatalog> subCatalogs = model.getSubCatalogs();
        List<SubCatalogDTO> subCatalogDTOS = new ArrayList<>();

        catalogDTO.setId(model.getId());
        catalogDTO.setName(model.getName());
        catalogDTO.setDescription(model.getDescription());
        catalogDTO.setStatus(model.isStatus());
        catalogDTO.setCreateDate(model.getCreateDate());
        catalogDTO.setLastUpdateDate(model.getLastUpdateDate());

        if(subCatalogs != null) {

            for(SubCatalog subCatalog : subCatalogs) {

                SubCatalogDTO subCatalogDTO = subConverter.convertToDTO(subCatalog);
                subCatalogDTOS.add(subCatalogDTO);
            }
        }
        catalogDTO.setSubCatalogs(subCatalogDTOS);

        return catalogDTO;
    }

    public List<CatalogDTO> convertToDTO(List<Catalog> models) {

        List<CatalogDTO> catalogDTOS = new ArrayList<>();
        for(Catalog catalog : models) {
            catalogDTOS.add(convertToDTO(catalog));
        }

        return catalogDTOS;
    }
}
