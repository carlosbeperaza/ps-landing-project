package com.ps.landing.project.services;

import com.ps.landing.project.models.Module;

public interface ModuleService {

	Module findById(long id);
    Module save();
    Module modify(long id);
    void delete(long id);
}
