package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Catalog")
public class CatalogController {

    private CatalogService service;

    @Autowired
    void setService(CatalogService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalog(@PathVariable Long id) {

        CatalogDTO catalogDTO = service.findById(id);
        //TODO: continuar y probar antes de realizar las demas acciones...
        return null;
    }
}
