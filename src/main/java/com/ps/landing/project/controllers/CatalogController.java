package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.models.Catalog;
import com.ps.landing.project.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService service;

    @Autowired
    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCatalogs() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<CatalogDTO> catalogDTOList = service.findAll();
            if(!catalogDTOList.isEmpty())
                response.put("Success", catalogDTOList);
            else
                response.put("Success", "list is empty");

            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        CatalogDTO catalogDTO = service.findById(id);
        ResponseEntity<?> responseEntity;

        if(catalogDTO != null) {
            response.put("Success", catalogDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("BadRequest", "No catalog with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCatalog(@RequestBody Catalog catalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            CatalogDTO catalogDTO = service.save(catalog);
            if(catalogDTO != null) {

                response.put("Success", catalogDTO);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("BadRequest", "this catalog name is already in use");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCatalog(@RequestBody Catalog catalog) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity = null;
        try {

            Object result = service.update(catalog);
            String resultType = result.getClass().getName();

            if(resultType.equals(CatalogDTO.class.getName())) {

                CatalogDTO updatedCatalog = (CatalogDTO) result;
                response.put("Success", updatedCatalog);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
            else if(resultType.equals(String.class.getName())) {

                response.put("BadRequest", result);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableCatalog(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Catalog disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("BadRequest", "No catalog with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
