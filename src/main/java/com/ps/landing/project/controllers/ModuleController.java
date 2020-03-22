package com.ps.landing.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.landing.project.dto.CatalogDTO;
import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.exceptions.CatalogException;
import com.ps.landing.project.exceptions.ModuleException;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.services.ModuleService;

@RestController
@RequestMapping("/Module")
public class ModuleController {
	
	private ModuleService service;

    @Autowired
    void setService(ModuleService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllModules() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<ModuleDTO> moduleDTOList = service.findAll();
            if(!moduleDTOList.isEmpty()) {

                response.put("Module list", moduleDTOList);
            } else {

                response.put("No modules", "list is empty");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModule(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ModuleDTO moduleDTO = service.findById(id);

        if(moduleDTO != null) {
            response.put("Module", moduleDTO);
        } else {
            response.put("Module", "No module with given id");
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addModule(@RequestBody Module module) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

        	ModuleDTO moduleDTO = service.save(module);
            response.put("Success", moduleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(ModuleException e) {

        	response.put("BadRequest", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateModule(@RequestBody Module module) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity = null;
        try {

        	ModuleDTO updatedModule = service.update(module);
            response.put("Success", updatedModule);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(ModuleException e) {

            response.put("BadRequest", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableModule(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Module disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No module with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error message", e.getMessage());
            response.put("Stack trace", e.getStackTrace());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
