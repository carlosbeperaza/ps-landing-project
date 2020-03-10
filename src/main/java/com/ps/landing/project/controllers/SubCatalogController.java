package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.models.SubCatalog;
import com.ps.landing.project.services.SubCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sub-catalog")
public class SubCatalogController {

    private SubCatalogService service;

    @Autowired
    public SubCatalogController(SubCatalogService service){
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSubCatalogs() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<SubCatalogDTO> subCatalogDTOList = service.findAll();
            if(!subCatalogDTOList.isEmpty())
                response.put("Sub catalog list", subCatalogDTOList);
            else
                response.put("No sub catalogs", "List is empty");
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        SubCatalogDTO subCatalogDTO = service.findById(id);

        if(subCatalogDTO != null) {
            response.put("Sub catalog", subCatalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("Sub catalog", "No sub catalog with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubCatalog(@RequestBody SubCatalog subCatalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SubCatalogDTO subCatalogDTO = service.save(subCatalog);
            response.put("New sub catalog", subCatalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubCatalog(@RequestBody SubCatalog subCatalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SubCatalogDTO updatedSubCatalog = service.update(subCatalog);
            if(updatedSubCatalog != null) {

                response.put("Updated sub catalog", updatedSubCatalog);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No sub catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableSubCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Sub catalog disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No sub catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
