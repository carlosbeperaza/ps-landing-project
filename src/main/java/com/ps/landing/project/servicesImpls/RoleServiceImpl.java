package com.ps.landing.project.servicesImpls;



import com.ps.landing.project.converters.RoleConverter;
import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.repos.ModuleRepo;
import com.ps.landing.project.repos.RoleRepo;
import com.ps.landing.project.services.RoleService;
import com.ps.landing.project.exceptions.RoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

	
	private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class.getName());
    private RoleRepo repo;
	private ModuleRepo moduleRepo;
    private RoleConverter converter;

    @Autowired
	RoleServiceImpl(RoleRepo repo, ModuleRepo moduleRepo, RoleConverter converter) {
    	this.repo = repo;
    	this.moduleRepo = moduleRepo;
    	this.converter = converter;
	}

	@Override
	public List<RoleDTO> findAll() {
		List<Role> roles = new ArrayList<>();
        repo.findAll().forEach(roles::add);
        return converter.convertToDTO(roles);
	}

	@Override
	@Transactional(readOnly = true)
	public RoleDTO findById(long id) {
		 Role role = repo.findById(id).orElse(null);
	     return (role != null) ? converter.convertToDTO(role) : null;
	}

	@Override
	@Transactional
	public RoleDTO save(Role role) throws RoleException {
		Role formerRole = repo.findByName(role.getName()).orElse(null);
		
		if(formerRole == null) {

			if(isValid(role)) {

				List<Module> modules = new ArrayList<>();

				for(Module module: role.getModules()) {
					modules.add(moduleRepo.findByName(module.getName()).orElse(null));
				}

				if(!modules.contains(null)) {

					role.setModules(modules);
					return converter.convertToDTO(repo.save(role));
				} else
					throw new RoleException("There's no module with given name");
			} else
				throw new RoleException("Operation aborted, role incomplete");
        } else
            throw new RoleException("This role name is already in use");
	}

	@Override
	@Transactional
	public RoleDTO update(Role role) throws RoleException {
		Role formerRole = repo.findById(role.getId()).orElse(null);
        if(formerRole != null) {

            if(role.getName() == null)
            	role.setName(formerRole.getName());
            if(role.getDescription() == null)
            	role.setDescription(formerRole.getDescription());
            
            role.setStatus(formerRole.isStatus());
            role.setCreateDate(formerRole.getCreateDate());
            
            Role formerRoleUpdate = repo.findByName(role.getName()).orElse(null);
            if(formerRoleUpdate == null)
            	return converter.convertToDTO(repo.save(role));
            else throw new RoleException("This role name is already in use");
        }else 
        	throw new RoleException("There's no role with given id");
        
	}

	@Override
	public boolean disable(long id) {
		Role role = repo.findById(id).orElse(null);
        if(role != null) {

            role.setStatus(false);
            repo.save(role);
            return true;
        }


		return false;
	}

	/**
	 * Método responsable de validar la integridad de una instancia de 'Role' en base a los siguientes puntos:
	 * <ul>
	 *     <li>
	 *         Los atributos sin valor por defecto al realizar un inserción SQL deben tener un valor deferente de nulo
	 *         o vacío.
	 *     </li>
	 *     <li>
	 *         La lista de módulos asociados debe tener al menos un modulo.
	 *     </li>
	 *     <li>
	 *         Los módulos asociados deben tener definido su atributo 'name' con un valor diferente de nulo o vacío.
	 *     </li>
	 * </ul>
	 * @param role instancia de 'Role' a validar
	 * @return <b>true</b> si se cumplen los puntos antes mencionados, <b>false</b> de lo contrario.
	 * */
	@Override
	public boolean isValid(Role role) {

		if(
				( role.getName() 			!= null && !role.getName().isEmpty()		) &&
				( role.getDescription() 	!= null && !role.getDescription().isEmpty()	) &&
				( role.getModules() 		!= null && role.getModules().size() > 0		)
		) {
			for (Module module: role.getModules()) {
				if(
						module.getName() == null ||
						module.getName().isEmpty() ||
						moduleRepo.findByName(module.getName()).orElse(null) == null
				)
					return false;
			}
			return true;
		} else
			return false;
	}
}
