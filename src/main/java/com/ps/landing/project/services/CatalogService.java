package com.ps.landing.project.services;

import com.ps.landing.project.models.Catalog;

public interface CatalogService {

    Catalog findById(long id);

    Catalog save();

    Catalog modify(long id);

    void delete(long id);
}
