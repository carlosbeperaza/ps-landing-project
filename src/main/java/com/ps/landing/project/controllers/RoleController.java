package com.ps.landing.project.controllers;

import java.util.ArrayList;
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
import com.ps.landing.project.dto.RoleDTO;
import com.ps.landing.project.exceptions.RoleException;
import com.ps.landing.project.models.Role;
import com.ps.landing.project.services.RoleService;

@RestController
@RequestMapping("/Role")
public class RoleController {
	
	private RoleService service;

    @Autowired
    void setService(RoleService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getAllRoles() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<RoleDTO> roleDTOList = service.findAll();
            if(!roleDTOList.isEmpty()) {

                response.put("roles",roleDTOList);
            } else {

                response.put("roles", new ArrayList<>());
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        RoleDTO roleDTO = service.findById(id);

        if(roleDTO != null) {
            response.put("role", roleDTO);
        } else {
            response.put("role", null);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

        	RoleDTO roleDTO = service.save(role);
            response.put("success", roleDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(RoleException e) {

            response.put("message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch(Exception e) {

            response.put("error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRole(@RequestBody Role role) {
        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

        	RoleDTO updatedRole = service.update(role);
            response.put("success", updatedRole);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(RoleException e) {

            response.put("message", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch(Exception e) {

            response.put("error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableRole(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("success", "Role disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("message", "No Role with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch(Exception e) {

            response.put("error", e.getMessage());
            //response.put("Stack trace", e.getStackTrace());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}