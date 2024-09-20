package com.pushup.antique.estadopersona.controller;

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

import com.pushup.antique.estadopersona.application.EstadoPersonaImpl;
import com.pushup.antique.estadopersona.domain.model.EstadoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estadopersona")
public class EstadoPersonaController {
    @Autowired
    private EstadoPersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createEstadoPersona(@Valid @RequestBody EstadoPersona estadoPersona,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estadoPersona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstadoPersona(@Valid @RequestBody EstadoPersona estadoPersona,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<EstadoPersona> estadoPersonaOpt = service.update(id, estadoPersona);
        if (estadoPersonaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoPersona> view(@PathVariable Long id){
        Optional<EstadoPersona> optEstadoPersona = service.findById(id);
        if(optEstadoPersona.isPresent()){
            return ResponseEntity.ok(optEstadoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstadoPersona> delete(@PathVariable Long id){
        EstadoPersona estadoPersona = new EstadoPersona();
        estadoPersona.setId(id);
        Optional<EstadoPersona> estadoPersonaOpt = service.deleteById(id);
        if (estadoPersonaOpt.isPresent()) {
            return ResponseEntity.ok(estadoPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<EstadoPersona> listEstadoPersona() {
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
