package com.pushup.antique.pais.controller;

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

import com.pushup.antique.pais.application.PaisImpl;
import com.pushup.antique.pais.domain.model.Pais;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {
    @Autowired
    private PaisImpl service;

    @PostMapping
    public ResponseEntity<?> createPais(@Valid @RequestBody Pais pais,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pais));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePais(@Valid @RequestBody Pais pais,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Pais> paisOpt = service.update(id, pais);
        if (paisOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(paisOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> view(@PathVariable Long id){
        Optional<Pais> optPais = service.findById(id);
        if(optPais.isPresent()){
            return ResponseEntity.ok(optPais.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pais> delete(@PathVariable Long id){
        Pais pais = new Pais();
        pais.setId(id);
        Optional<Pais> paisOpt = service.deleteById(id);
        if (paisOpt.isPresent()) {
            return ResponseEntity.ok(paisOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Pais> listPais() {
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
