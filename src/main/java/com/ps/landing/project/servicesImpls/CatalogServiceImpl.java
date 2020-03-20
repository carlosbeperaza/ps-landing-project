package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.CatalogConverter;
import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.models.SubCatalog;
import com.ps.landing.project.repos.CatalogRepo;
import com.ps.landing.project.repos.SubCatalogRepo;
import com.ps.landing.project.services.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());
    private CatalogRepo repo;
    private SubCatalogRepo subRepo;
    private CatalogConverter converter;

    @Autowired
    public CatalogServiceImpl(CatalogRepo repo, SubCatalogRepo subRepo, CatalogConverter converter) {
        this.repo = repo;
        this.subRepo = subRepo;
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CatalogDTO> findAll() {

        List<Catalog> catalogs = new ArrayList<>();
        repo.findAll().forEach(catalogs::add);

        return converter.convertToDTO(catalogs);
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogDTO findById(Long id) {

        Catalog catalog = repo.findById(id).orElse(null);

        return (catalog != null) ? converter.convertToDTO(catalog) : null;
    }

    @Override
    @Transactional
    public CatalogDTO save(Catalog catalog) {

        Catalog coincidence = repo.findByName(catalog.getName()).orElse(null);
        if(coincidence == null) {
            catalog.setSubCatalogs(new ArrayList<>());
            return converter.convertToDTO(repo.save(catalog));
        }
        else
            return null;
    }

    @Override
    @Transactional
    public CatalogDTO update(Catalog catalog) {

        Catalog formerCatalog = repo.findById(catalog.getId()).orElse(null);
        if(formerCatalog != null) {

            if(catalog.getName() == null)
                catalog.setName(formerCatalog.getName());
            if(catalog.getDescription() == null)
                catalog.setDescription(formerCatalog.getDescription());
            if(catalog.getSubCatalogs() == null || catalog.getSubCatalogs().isEmpty())
                catalog.setSubCatalogs(formerCatalog.getSubCatalogs());
            catalog.setStatus(formerCatalog.isStatus());
            catalog.setCreateDate(formerCatalog.getCreateDate());
            catalog.setLastUpdateDate(new Date());

            Catalog coincidence = repo.findByNameAndIdNot(catalog.getName(), catalog.getId()).orElse(null);
            if(coincidence == null)
                return converter.convertToDTO(repo.save(catalog));
        }
        return null;
    }

    @Override
    @Transactional
    public boolean disable(Long id) {

        Catalog catalog = repo.findById(id).orElse(null);
        if(catalog != null) {

            for(SubCatalog subCatalog : catalog.getSubCatalogs()) {

                subCatalog.setStatus(false);
                subRepo.save(subCatalog);
            }
            catalog.setStatus(false);
            repo.save(catalog);
            return true;
        }
        return false;
    }
}