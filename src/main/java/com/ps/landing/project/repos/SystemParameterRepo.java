package com.ps.landing.project.repos;

import com.ps.landing.project.models.SystemParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SystemParameterRepo extends CrudRepository<SystemParameter, Long> {

    Optional<SystemParameter> findByName(String name);
    Optional<SystemParameter> findByNameAndIdNot(String name, Long id);
}
