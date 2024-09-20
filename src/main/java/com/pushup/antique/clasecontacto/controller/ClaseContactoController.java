package com.pushup.antique.clasecontacto.controller;

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


import com.pushup.antique.clasecontacto.application.ClaseContactoImpl;
import com.pushup.antique.clasecontacto.domain.model.ClaseContacto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clasecontacto")
public class ClaseContactoController {
    @Autowired
    private ClaseContactoImpl service;
    @PostMapping
    public ResponseEntity<?> createClaseContacto(@Valid @RequestBody ClaseContacto claseContacto,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(claseContacto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClaseContacto(@Valid @RequestBody ClaseContacto claseContacto,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ClaseContacto> claseContactoOpt = service.update(id, claseContacto);
        if (claseContactoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(claseContactoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseContacto> view(@PathVariable Long id){
        Optional<ClaseContacto> optionalClaseContacto = service.findById(id);
        if(optionalClaseContacto.isPresent()){
            return ResponseEntity.ok(optionalClaseContacto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClaseContacto> delete(@PathVariable Long id){
        ClaseContacto claseContacto = new ClaseContacto();
        claseContacto.setId(id);
        Optional<ClaseContacto> claseContactoOptional = service.deleteById(id);
        if (claseContactoOptional.isPresent()) {
            return ResponseEntity.ok(claseContactoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<ClaseContacto> listClaseContacto() {
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
