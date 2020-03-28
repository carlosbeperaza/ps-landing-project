package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.CatalogConverter;
import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.exceptions.CatalogException;
import com.ps.landing.project.exceptions.SubCatalogException;
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
    public CatalogDTO save(Catalog catalog) throws CatalogException {

        Catalog coincidence = repo.findByName(catalog.getName()).orElse(null);
        if(coincidence == null) {

            List<SubCatalog> subCatalogs = catalog.getSubCatalogs();
            catalog.setSubCatalogs(new ArrayList<>());
            Catalog newCatalog = repo.save(catalog);
            List<SubCatalog> newSubCatalogs = new ArrayList<>();
            for(SubCatalog subCatalog: subCatalogs) {

                subCatalog.setParent(newCatalog.getId());
                SubCatalog subCoincidence = subRepo.findByNameAndParent(
                        subCatalog.getName(), subCatalog.getParent()
                ).orElse(null);
                if(subCoincidence == null) {

                    subRepo.save(subCatalog);
                    newSubCatalogs.add(subCatalog);
                }
            }
            newCatalog.setSubCatalogs(newSubCatalogs);
            return converter.convertToDTO(newCatalog);
        }
        else throw new CatalogException("This catalog name is already in use");
    }

    @Override
    @Transactional
    public CatalogDTO update(Catalog catalog) throws CatalogException {

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
            else throw new CatalogException("This catalog name is already in use");
        } else throw new CatalogException("There's no catalog with given id");
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