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

import com.ps.landing.project.dto.SubModuleDTO;
import com.ps.landing.project.models.SubModule;
import com.ps.landing.project.services.SubModuleService;

@RestController
@RequestMapping("/SubModule")
public class SubModuleController {
	
	private SubModuleService service;

    @Autowired
    public SubModuleController(SubModuleService service){
        this.service = service;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getAllSubModules() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<SubModuleDTO> subModuleDTOList = service.findAll();
            if(!subModuleDTOList.isEmpty())
                response.put("Sub Module list", subModuleDTOList);
            else
                response.put("No sub Modules", "List is empty");
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubModule(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        SubModuleDTO subModuleDTO = service.findById(id);

        if(subModuleDTO != null) {
            response.put("Sub Module", subModuleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("Sub Module", "No sub Module with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addSubModule(@RequestBody SubModule subModule) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SubModuleDTO subModuleDTO = service.save(subModule);
            response.put("New sub catalogModule", subModuleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateSubModule(@RequestBody SubModule subModule) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SubModuleDTO updatedSubModule = service.update(subModule);
            if(updatedSubModule != null) {

                response.put("Updated sub Module", updatedSubModule);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No sub Module with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableSubModule(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Sub Module disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No sub Module with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
