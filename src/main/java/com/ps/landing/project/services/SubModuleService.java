package com.ps.landing.project.services;

import com.ps.landing.project.models.SubModule;

public interface SubModuleService {
	
	SubModule findById(long id);
    SubModule save();
    SubModule modify(long id);
    void delete(long id);

}
