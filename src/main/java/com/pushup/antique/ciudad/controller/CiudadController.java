package com.pushup.antique.ciudad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pushup.antique.ciudad.application.CiudadImpl;
import com.pushup.antique.ciudad.domain.model.Ciudad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
    private CiudadImpl service;

    @PostMapping
    public ResponseEntity<?> createCiudad(@Valid @RequestBody Ciudad ciudad,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ciudad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCiudad(@Valid @RequestBody Ciudad ciudad,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Ciudad> ciudadOpt = service.update(id, ciudad);
        if (ciudadOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ciudadOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> view(@PathVariable Long id){
        Optional<Ciudad> optionalCiudad = service.findById(id);
        if(optionalCiudad.isPresent()){
            return ResponseEntity.ok(optionalCiudad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ciudad> delete(@PathVariable Long id){
        Ciudad ciudad = new Ciudad();
        ciudad.setId(id);
        Optional<Ciudad> ciudadOptional = service.deleteById(id);
        if (ciudadOptional.isPresent()) {
            return ResponseEntity.ok(ciudadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Ciudad> listCiudad() {
        return service.findAll();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
