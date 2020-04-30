package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.SubCatalogDTO;
import com.ps.landing.project.exceptions.SubCatalogException;
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
                response.put("Success", subCatalogDTOList);
            else
                response.put("Success", "List is empty");
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
            response.put("Success", subCatalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("NotAcceptable", "No sub catalog with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubCatalog(@RequestBody SubCatalog subCatalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SubCatalogDTO subCatalogDTO = service.save(subCatalog);
            response.put("Success", subCatalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(SubCatalogException e) {

            response.put("NotAcceptable", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
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
            response.put("Success", updatedSubCatalog);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(SubCatalogException e) {

            response.put("NotAcceptable", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableSubCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Sub catalog disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("NotAcceptable", "No sub catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
