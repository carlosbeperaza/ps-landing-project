package com.ps.landing.project.servicesImpls;


import com.ps.landing.project.models.Role;
import com.ps.landing.project.repos.RoleRepo;
import com.ps.landing.project.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class.getName());
    private RoleRepo roleRepo;

    @Autowired
    void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(long id) {

        Optional<Role> optionalRole = roleRepo.findById(id);
        return optionalRole.orElse(null);
    }

    @Override
    public Role save() {


        return null;
    }

    @Override
    public Role modify(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
