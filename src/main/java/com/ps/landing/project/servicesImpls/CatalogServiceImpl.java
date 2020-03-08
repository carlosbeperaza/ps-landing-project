package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.CatalogConverter;
import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.SubCatalog;
import com.ps.landing.project.repos.CatalogRepo;
import com.ps.landing.project.services.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());
    
    @Autowired
    private CatalogRepo repo;
    
    @Autowired
    private CatalogConverter converter;


    @Override
    public List<CatalogDTO> findAll() {

        List<Catalog> catalogs = new ArrayList<>();
        repo.findAll().forEach(catalogs::add);

        return converter.convertToDTO(catalogs);
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogDTO findById(long id) {

        Catalog catalog = repo.findById(id).orElse(null);

        return (catalog != null) ? converter.convertToDTO(catalog) : null;
    }

    @Override
    public CatalogDTO save(Catalog catalog) {
        return converter.convertToDTO(repo.save(catalog));
    }

    @Override
    public CatalogDTO update(Catalog catalog) {
        return converter.convertToDTO(repo.save(catalog));
    }

    @Override
    public void disable(long id) {

        Catalog catalog = repo.findById(id).orElse(null);
        if(catalog != null) {

            List<SubCatalog> subCatalogs = new ArrayList<>();

            for(SubCatalog subCatalog : catalog.getSubCatalogs()) {

                subCatalog.setStatus(false);
                subCatalogs.add(subCatalog);
            }
            catalog.setStatus(false);
            catalog.setSubCatalogs(subCatalogs);
            repo.save(catalog);
        }
    }
}