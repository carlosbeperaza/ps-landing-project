package com.ps.landing.project.servicesImpls;

import com.ps.landing.project.converters.SubCatalogConverter;
import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.SubCatalog;
import com.ps.landing.project.repos.SubCatalogRepo;
import com.ps.landing.project.services.SubCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCatalogServiceImpl implements SubCatalogService {

    private SubCatalogRepo repo;
    private SubCatalogConverter converter;

    @Autowired
    public SubCatalogServiceImpl(SubCatalogRepo repo, SubCatalogConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    @Transactional
    public List<SubCatalogDTO> findAll() {

        List<SubCatalog> subCatalogs = new ArrayList<>();
        repo.findAll().forEach(subCatalogs::add);

        return converter.convertToDTO(subCatalogs);
    }

    @Override
    @Transactional
    public SubCatalogDTO findById(Long id) {

        SubCatalog subCatalog = repo.findById(id).orElse(null);

        return (subCatalog != null) ? converter.convertToDTO(subCatalog) : null;
    }

    @Override
    public SubCatalogDTO save(SubCatalog subCatalog) {
        return converter.convertToDTO(repo.save(subCatalog));
    }

    @Override
    public SubCatalogDTO update(SubCatalog subCatalog) {

        if(repo.findById(subCatalog.getId()).isPresent()) {

            return converter.convertToDTO(repo.save(subCatalog));
        }
        return null;
    }

    @Override
    public boolean disable(Long id) {

        SubCatalog subCatalog = repo.findById(id).orElse(null);
        if(subCatalog != null) {

            subCatalog.setStatus(false);
            repo.save(subCatalog);
            return true;
        }
        return false;
    }
}
