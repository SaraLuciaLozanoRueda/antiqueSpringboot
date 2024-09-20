package com.pushup.antique.persona.controller;

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

import com.pushup.antique.persona.application.PersonaImpl;
import com.pushup.antique.persona.domain.model.Persona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createPersona(@Valid @RequestBody Persona persona,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@Valid @RequestBody Persona persona,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Persona> personaOpt = service.update(id, persona);
        if (personaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> view(@PathVariable Long id){
        Optional<Persona> optPersona = service.findById(id);
        if(optPersona.isPresent()){
            return ResponseEntity.ok(optPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id){
        Persona persona = new Persona();
        persona.setId(id);
        Optional<Persona> personaOpt = service.deleteById(id);
        if (personaOpt.isPresent()) {
            return ResponseEntity.ok(personaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Persona> listPersona() {
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
