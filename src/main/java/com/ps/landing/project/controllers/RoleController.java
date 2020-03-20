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


import com.ps.landing.project.dto.RoleDTO;
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

                response.put("Role list",roleDTOList);
            } else {

                response.put("No roles", "list is empty");
            }
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        RoleDTO roleDTO = service.findById(id);

        if(roleDTO != null) {
            response.put("Role", roleDTO);
        } else {
            response.put("Role", "No role with given id");
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            RoleDTO roleDTO = service.save(role);
            if(roleDTO != null) {
            	response.put("New role", roleDTO);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }else {
            	response.put("Error", "this role name is already in use");
                responseEntity = new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            
        } catch(Exception e) {

            response.put("Error", e.getMessage());
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
            if(updatedRole != null) {

                response.put("Updated role", updatedRole);
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Error", "No role with given id or name in use");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableRole(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "Role disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("Bad request", "No Role with given id");
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