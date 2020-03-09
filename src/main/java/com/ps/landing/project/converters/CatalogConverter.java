package com.ps.landing.project.converters;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.SubCatalog;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component("CatalogConverter")
public class CatalogConverter {

    public Catalog convertToModel(CatalogDTO dto) {

        Catalog catalog = new Catalog();
        List<SubCatalogDTO> subCatalogDTOS = dto.getSubCatalogs();
        List<SubCatalog> subCatalogs = new ArrayList<>();

        catalog.setId(dto.getId());
        catalog.setName(dto.getName());
        catalog.setDescription(dto.getDescription());
        catalog.setStatus(dto.isStatus());
        catalog.setCreateDate((Timestamp) dto.getCreateDate());
        catalog.setLastUpdateDate((Timestamp) dto.getLastUpdateDate());

        if(subCatalogDTOS != null) {

            for(SubCatalogDTO subCatalogDTO : subCatalogDTOS) {

                SubCatalog subCatalog = new SubCatalog();

                subCatalog.setId(subCatalogDTO.getId());
                subCatalog.setName(subCatalogDTO.getName());
                subCatalog.setDescription(subCatalogDTO.getDescription());
                subCatalog.setParent(dto.getId());
                //url
                //icon
                subCatalog.setStatus(subCatalogDTO.isStatus());
                subCatalog.setCreateDate((Timestamp) subCatalogDTO.getCreateDate());
                subCatalog.setLastUpdateDate((Timestamp) subCatalogDTO.getLastUpdateDate());

                subCatalogs.add(subCatalog);
            }
        }
        catalog.setSubCatalogs(subCatalogs);

        return catalog;
    }

    public List<Catalog> convertToModel(List<CatalogDTO> dtoList) {

        List<Catalog> catalogs = new ArrayList<>();

        for(CatalogDTO catalogDTO : dtoList) {

            Catalog catalog = convertToModel(catalogDTO);
            catalogs.add(catalog);
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

                SubCatalogDTO subCatalogDTO = new SubCatalogDTO();

                subCatalogDTO.setId(subCatalog.getId());
                subCatalogDTO.setName(subCatalog.getName());
                subCatalogDTO.setDescription(subCatalog.getDescription());
                subCatalogDTO.setStatus(subCatalog.isStatus());
                subCatalogDTO.setCreateDate(subCatalog.getCreateDate());
                subCatalogDTO.setLastUpdateDate(subCatalog.getLastUpdateDate());

                subCatalogDTOS.add(subCatalogDTO);
            }
        }
        catalogDTO.setSubCatalogs(subCatalogDTOS);

        return catalogDTO;
    }

    public List<CatalogDTO> convertToDTO(List<Catalog> catalogs) {

        List<CatalogDTO> catalogDTOS = new ArrayList<>();

        for(Catalog catalog : catalogs) {

            CatalogDTO catalogDTO = convertToDTO(catalog);
            catalogDTOS.add(catalogDTO);
        }

        return catalogDTOS;
    }
}
