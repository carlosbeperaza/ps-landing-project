package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService service;

    @Autowired
    void setService(CatalogService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        CatalogDTO catalogDTO = service.findById(id);

        if(catalogDTO != null) {
            response.put("Catalog", catalogDTO);
        } else {
            response.put("Catalog", "No catalog with given id");
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCatalog(@RequestBody Catalog catalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            CatalogDTO catalogDTO = service.save(catalog);
            response.put("New catalog", catalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCatalog(@RequestBody Catalog catalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            CatalogDTO current = service.findById(catalog.getId());
            if(current != null) {

                CatalogDTO updatedCatalog = service.update(catalog);
                response.put("Old catalog", current);
                response.put("Updated catalog", updatedCatalog);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            CatalogDTO formerCatalog = service.findById(id);
            if(formerCatalog != null) {

                service.delete(id);
                response.put("Success", "Catalog deleted");
                response.put("Former catalog", formerCatalog);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
