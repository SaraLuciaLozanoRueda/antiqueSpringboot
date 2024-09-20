package com.pushup.antique.coleccionista.controller;

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

import com.pushup.antique.coleccionista.application.ColeccionistaImpl;
import com.pushup.antique.coleccionista.domain.model.Coleccionista;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/coleccionistas")
public class ColeccionistaController {
    @Autowired
    private ColeccionistaImpl service;

    @PostMapping
    public ResponseEntity<?> createColeccionista(@Valid @RequestBody Coleccionista coleccionista,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(coleccionista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColeccionista(@Valid @RequestBody Coleccionista coleccionista,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Coleccionista> coleccionistaOpt = service.update(id, coleccionista);
        if (coleccionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(coleccionistaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleccionista> view(@PathVariable Long id){
        Optional<Coleccionista> optColeccionista = service.findById(id);
        if(optColeccionista.isPresent()){
            return ResponseEntity.ok(optColeccionista.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Coleccionista> delete(@PathVariable Long id){
        Coleccionista coleccionista = new Coleccionista();
        coleccionista.setId(id);
        Optional<Coleccionista> coleccionistaOpt = service.deleteById(id);
        if (coleccionistaOpt.isPresent()) {
            return ResponseEntity.ok(coleccionistaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Coleccionista> listColeccionista() {
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
