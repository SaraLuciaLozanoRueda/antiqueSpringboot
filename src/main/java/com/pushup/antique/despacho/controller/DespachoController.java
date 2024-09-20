package com.pushup.antique.despacho.controller;

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

import com.pushup.antique.despacho.application.DespachoImpl;
import com.pushup.antique.despacho.domain.model.Despacho;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/despachos")
public class DespachoController {
    @Autowired
    private DespachoImpl service;

    @PostMapping
    public ResponseEntity<?> createDespacho(@Valid @RequestBody Despacho despacho,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(despacho));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDespacho(@Valid @RequestBody Despacho despacho,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Despacho> despachoOpt = service.update(id, despacho);
        if (despachoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(despachoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despacho> view(@PathVariable Long id){
        Optional<Despacho> optDespacho = service.findById(id);
        if(optDespacho.isPresent()){
            return ResponseEntity.ok(optDespacho.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Despacho> delete(@PathVariable Long id){
        Despacho despacho = new Despacho();
        despacho.setId(id);
        Optional<Despacho> despachoOpt = service.deleteById(id);
        if (despachoOpt.isPresent()) {
            return ResponseEntity.ok(despachoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Despacho> listDespacho() {
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
