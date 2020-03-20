package com.ps.landing.project.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ps.landing.project.models.Module;

public interface ModuleRepo extends CrudRepository<Module, Long>{
	
	Optional<Module>findByName(String name);
	Optional<Module> findByNameAndIdNot(String name, Long id);

}
