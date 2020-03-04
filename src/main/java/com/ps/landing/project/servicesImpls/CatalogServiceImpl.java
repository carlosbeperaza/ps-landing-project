package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.CatalogConverter;
import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.repos.CatalogRepo;
import com.ps.landing.project.services.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    private Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());
    private CatalogRepo repo;
    private CatalogConverter converter;

    @Autowired
    void setRepo(CatalogRepo repo) {
        this.repo = repo;
    }

    @Autowired
    void setConverter(CatalogConverter converter) {
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogDTO findById(long id) {

        Optional<Catalog> optionalCatalog = repo.findById(id);
        Catalog catalog = optionalCatalog.orElse(null);

        return (catalog != null) ? converter.convertToDTO(catalog) : null;
    }

    @Override
    public CatalogDTO save(Catalog catalog) {

        repo.save(catalog);
        return converter.convertToDTO(catalog);
    }

    @Override
    public CatalogDTO update(Catalog catalog) {

        repo.save(catalog);
        return converter.convertToDTO(catalog);
    }

    @Override
    public void delete(long id) {
        repo.deleteById(id);
    }
}
