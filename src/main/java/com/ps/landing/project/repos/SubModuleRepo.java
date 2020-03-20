package com.ps.landing.project.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ps.landing.project.models.SubModule;

public interface SubModuleRepo extends CrudRepository<SubModule, Long> {
	
	Optional<SubModule>findByNameAndParent(String name, Long parent);
	Optional<SubModule>findByNameAndIdNotAndParent(String name,Long id, Long parent);

}
