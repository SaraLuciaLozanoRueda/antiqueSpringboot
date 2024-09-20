package com.pushup.antique.personatipopersona.controller;

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

import com.pushup.antique.personatipopersona.application.PersonaTipoPersonaImpl;
import com.pushup.antique.personatipopersona.domain.model.PersonaTipoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/personatipopersona")
public class PersonaTipoPersonaController {
    @Autowired
    private PersonaTipoPersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createPersonaTipoPersona(@Valid @RequestBody PersonaTipoPersona PTP,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(PTP));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonaTipoPersona(@Valid @RequestBody PersonaTipoPersona PTP,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<PersonaTipoPersona> PTPOpt = service.update(id, PTP);
        if (PTPOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(PTPOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaTipoPersona> view(@PathVariable Long id){
        Optional<PersonaTipoPersona> optPTP = service.findById(id);
        if(optPTP.isPresent()){
            return ResponseEntity.ok(optPTP.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaTipoPersona> delete(@PathVariable Long id){
        PersonaTipoPersona PTP = new PersonaTipoPersona();
        PTP.setId(id);
        Optional<PersonaTipoPersona> PTPOpt = service.deleteById(id);
        if (PTPOpt.isPresent()) {
            return ResponseEntity.ok(PTPOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<PersonaTipoPersona> listPersonaTipoPersona() {
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
