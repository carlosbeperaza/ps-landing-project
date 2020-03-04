package com.ps.landing.project.servicesImpls;

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
    private CatalogRepo catalogRepo;

    @Autowired
    void setCatalogRepo(CatalogRepo catalogRepo) {
        this.catalogRepo = catalogRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Catalog findById(long id) {

        Optional<Catalog> optionalCatalog = catalogRepo.findById(id);
        return optionalCatalog.orElse(null);
    }

    @Override
    public Catalog save() {


        return null;
    }

    @Override
    public Catalog modify(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
