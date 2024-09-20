package com.pushup.antique.genero.controller;

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

import com.pushup.antique.genero.application.GeneroImpl;
import com.pushup.antique.genero.domain.model.Genero;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private GeneroImpl service;

    @PostMapping
    public ResponseEntity<?> createGenero(@Valid @RequestBody Genero genero,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(genero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenero(@Valid @RequestBody Genero genero,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Genero> generoOpt = service.update(id, genero);
        if (generoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> view(@PathVariable Long id){
        Optional<Genero> optGenero = service.findById(id);
        if(optGenero.isPresent()){
            return ResponseEntity.ok(optGenero.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genero> delete(@PathVariable Long id){
        Genero genero = new Genero();
        genero.setId(id);
        Optional<Genero> generoOpt = service.deleteById(id);
        if (generoOpt.isPresent()) {
            return ResponseEntity.ok(generoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Genero> listGenero() {
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
