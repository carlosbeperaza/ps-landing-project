package com.ps.landing.project.services;

import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.SubCatalog;

import java.util.List;

public interface SubCatalogService {

    List<SubCatalogDTO> findAll();
    SubCatalogDTO findById(Long id);
    SubCatalogDTO save(SubCatalog subCatalog);
    SubCatalogDTO update(SubCatalog subCatalog);
    boolean disable(Long id);
}
