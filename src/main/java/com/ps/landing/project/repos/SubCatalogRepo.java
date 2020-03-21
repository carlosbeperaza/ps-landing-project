package com.ps.landing.project.repos;

import com.ps.landing.project.models.SubCatalog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubCatalogRepo extends CrudRepository<SubCatalog, Long> {

    Optional<SubCatalog> findByNameAndParent(String name, Long parent);
    Optional<SubCatalog> findByNameAndIdNotAndParent(String name, Long id, Long parent);
}
