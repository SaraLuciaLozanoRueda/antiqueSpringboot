package com.pushup.antique.direccionpersona.controller;

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

import com.pushup.antique.direccionpersona.application.DireccionPersonaImpl;
import com.pushup.antique.direccionpersona.domain.model.DIreccionPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/direccionpersonas")
public class DireccionPersonaController {
    @Autowired
    private DireccionPersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createDIreccionPersona(@Valid @RequestBody DIreccionPersona direccionPersona,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(direccionPersona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDIreccionPersona(@Valid @RequestBody DIreccionPersona direccionPersona,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<DIreccionPersona> direccionPersonaOpt = service.update(id, direccionPersona);
        if (direccionPersonaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(direccionPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DIreccionPersona> view(@PathVariable Long id){
        Optional<DIreccionPersona> optDireccionPersona = service.findById(id);
        if(optDireccionPersona.isPresent()){
            return ResponseEntity.ok(optDireccionPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DIreccionPersona> delete(@PathVariable Long id){
        DIreccionPersona direccionPersona = new DIreccionPersona();
        direccionPersona.setId(id);
        Optional<DIreccionPersona> direccionPersonaOpt = service.deleteById(id);
        if (direccionPersonaOpt.isPresent()) {
            return ResponseEntity.ok(direccionPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<DIreccionPersona> listDIreccionPersona() {
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
