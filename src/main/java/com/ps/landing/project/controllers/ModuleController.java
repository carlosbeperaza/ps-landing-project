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

import com.ps.landing.project.dto.ModuleDTO;
import com.ps.landing.project.exceptions.ModuleException;
import com.ps.landing.project.models.Module;
import com.ps.landing.project.services.ModuleService;

import  org.springframework.http.MediaType ;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ps.landing.project.converters.ModuleConverter;



@Controller
@RequestMapping("/Module")
public class ModuleController {
	
	private ModuleService service;
	
	@Autowired
	private ModuleConverter moduleConverter;

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

                response.put("Success", moduleDTOList);
            } else {

                response.put("Success", "list is empty");
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
        ResponseEntity<?> responseEntity;

        if(moduleDTO != null) {
            response.put("Success", moduleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("message", "No module with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        return responseEntity;
    }

    @PostMapping("/add")
    
    @ResponseBody
    public ResponseEntity<?> addModule(@RequestBody ModuleDTO module) {
    	
    Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

        	ModuleDTO moduleDTO = service.save(moduleConverter.convertToModel(module));
            response.put("Success", moduleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(ModuleException e) {

        	response.put("message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
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

            response.put("message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableModule(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Module disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("message", "No module with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch(Exception e) {

            response.put("Error message", e.getMessage());
            response.put("Stack trace", e.getStackTrace());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
