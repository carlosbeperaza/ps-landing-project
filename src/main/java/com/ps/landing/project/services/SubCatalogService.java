package com.ps.landing.project.services;

import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.exceptions.SubCatalogException;
import com.ps.landing.project.models.SubCatalog;

import java.util.List;

public interface SubCatalogService {

    List<SubCatalogDTO> findAll();
    SubCatalogDTO findById(Long id);
    SubCatalogDTO save(SubCatalog subCatalog) throws SubCatalogException;
    SubCatalogDTO update(SubCatalog subCatalog) throws SubCatalogException;
    boolean disable(Long id);
}
