package com.ps.landing.project.repos;

import com.ps.landing.project.models.Catalog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatalogRepo extends CrudRepository<Catalog, Long> {

    Optional<Catalog> findByName(String name);
    Optional<Catalog> findByNameAndIdNot(String name, Long id);
}