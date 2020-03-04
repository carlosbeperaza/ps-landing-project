package com.ps.landing.project.services;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;

public interface CatalogService {

    CatalogDTO findById(long id);
    CatalogDTO save(Catalog catalog);
    CatalogDTO update(Catalog catalog);
    void delete(long id);
}
