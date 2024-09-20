package com.pushup.antique.galeria.controller;

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

import com.pushup.antique.galeria.application.GaleriaImpl;
import com.pushup.antique.galeria.domain.model.Galeria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/galeria")
public class GaleriaController {
    @Autowired
    private GaleriaImpl service;

    @PostMapping
    public ResponseEntity<?> createGaleria(@Valid @RequestBody Galeria galeria,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(galeria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGaleria(@Valid @RequestBody Galeria galeria,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Galeria> galeriaOpt = service.update(id, galeria);
        if (galeriaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(galeriaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Galeria> view(@PathVariable Long id){
        Optional<Galeria> optGaleria = service.findById(id);
        if(optGaleria.isPresent()){
            return ResponseEntity.ok(optGaleria.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Galeria> delete(@PathVariable Long id){
        Galeria galeria = new Galeria();
        galeria.setId(id);
        Optional<Galeria> galeriaOpt = service.deleteById(id);
        if (galeriaOpt.isPresent()) {
            return ResponseEntity.ok(galeriaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Galeria> listGaleria() {
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
