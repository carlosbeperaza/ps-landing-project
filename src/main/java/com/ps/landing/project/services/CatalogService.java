package com.ps.landing.project.services;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;

import java.util.List;

public interface CatalogService {

    List<CatalogDTO> findAll();
    CatalogDTO findById(Long id);
    CatalogDTO save(Catalog catalog);
    CatalogDTO update(Catalog catalog);

    /**
     * Método que deshabilita al catálogo indicado por su id, de igual manera se deshabilitan los sub-catálogos de este.
     * @param id id del catálogo a deshabilitar.
     * @return true si el catálogo fue deshabilitado, de lo contrario false.
     * */
    boolean disable(Long id);
}
