package com.ps.landing.project.controllers;

import com.ps.landing.project.dto.SystemParameterDTO;
import com.ps.landing.project.exceptions.SystemParameterException;
import com.ps.landing.project.models.SystemParameter;
import com.ps.landing.project.services.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system-parameter")
public class SystemParameterController {

    private SystemParameterService service;

    @Autowired
    public SystemParameterController(SystemParameterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSystemParameters() {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            List<SystemParameterDTO> systemParameterDTOList = service.findAll();
            if(!systemParameterDTOList.isEmpty())
                response.put("Success", systemParameterDTOList);
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
    public ResponseEntity<?> getSystemParameter(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        SystemParameterDTO systemParameterDTO = service.findById(id);
        ResponseEntity<?> responseEntity;

        if(systemParameterDTO != null) {
            response.put("Success", systemParameterDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            response.put("BadRequest", "No system parameter with given id");
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSystemParameter(@RequestBody SystemParameter systemParameter) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SystemParameterDTO systemParameterDTO = service.save(systemParameter);
            response.put("Success", systemParameterDTO);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(SystemParameterException e) {

            response.put("BadRequest", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSystemParameter(@RequestBody SystemParameter systemParameter) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            SystemParameterDTO updatedSystemParameter = service.update(systemParameter);
            response.put("Success", updatedSystemParameter);
            responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(SystemParameterException e) {

            response.put("BadRequest", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }  catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableSystemParameter(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity;
        try {

            if(service.disable(id)) {

                response.put("Success", "System parameter disabled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                response.put("BadRequest", "No system parameter with given id");
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {

            response.put("Error", e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}