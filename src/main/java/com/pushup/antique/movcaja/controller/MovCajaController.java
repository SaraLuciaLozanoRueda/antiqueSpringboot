package com.pushup.antique.movcaja.controller;

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

import com.pushup.antique.movcaja.application.MovCajaImpl;
import com.pushup.antique.movcaja.domain.model.MovCaja;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movcaja")
public class MovCajaController {
    @Autowired
    private MovCajaImpl service;

    @PostMapping
    public ResponseEntity<?> createMovCaja(@Valid @RequestBody MovCaja movCaja,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(movCaja));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovCaja(@Valid @RequestBody MovCaja movCaja,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<MovCaja> movCajaOpt = service.update(id, movCaja);
        if (movCajaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movCajaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovCaja> view(@PathVariable Long id){
        Optional<MovCaja> optMovCaja = service.findById(id);
        if(optMovCaja.isPresent()){
            return ResponseEntity.ok(optMovCaja.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovCaja> delete(@PathVariable Long id){
        MovCaja movCaja = new MovCaja();
        movCaja.setId(id);
        Optional<MovCaja> movCajaOpt = service.deleteById(id);
        if (movCajaOpt.isPresent()) {
            return ResponseEntity.ok(movCajaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<MovCaja> listMovCaja() {
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
