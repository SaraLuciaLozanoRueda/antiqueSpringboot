package com.pushup.antique.contactopersona.controller;

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

import com.pushup.antique.contactopersona.application.ContactoPersonaImpl;
import com.pushup.antique.contactopersona.domain.model.ContactoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contactopersona")
public class ContactoPersonaController {
    @Autowired
    private ContactoPersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createContactoPersona(@Valid @RequestBody ContactoPersona contactoPersona,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(contactoPersona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContactoPersona(@Valid @RequestBody ContactoPersona contactoPersona,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ContactoPersona> contactoPersonaOpt = service.update(id, contactoPersona);
        if (contactoPersonaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(contactoPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoPersona> view(@PathVariable Long id){
        Optional<ContactoPersona> optContactoPersona = service.findById(id);
        if(optContactoPersona.isPresent()){
            return ResponseEntity.ok(optContactoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactoPersona> delete(@PathVariable Long id){
        ContactoPersona contactoPersona = new ContactoPersona();
        contactoPersona.setId(id);
        Optional<ContactoPersona> contactoPersonaOpt = service.deleteById(id);
        if (contactoPersonaOpt.isPresent()) {
            return ResponseEntity.ok(contactoPersonaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<ContactoPersona> listContactoPersona() {
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
