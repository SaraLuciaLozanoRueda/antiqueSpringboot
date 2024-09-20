package com.pushup.antique.tipodireccion.controller;

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

import com.pushup.antique.tipodireccion.application.TipoDireccionImpl;
import com.pushup.antique.tipodireccion.domain.model.TipoDIreccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipodireccion")
public class TipoDireccionController {
    @Autowired
    private TipoDireccionImpl service;

    @PostMapping
    public ResponseEntity<?> createTipoDIreccion(@Valid @RequestBody TipoDIreccion tipoDireccion,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tipoDireccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipoDIreccion(@Valid @RequestBody TipoDIreccion tipoDireccion,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TipoDIreccion> TPDOpt = service.update(id, tipoDireccion);
        if (TPDOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TPDOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDIreccion> view(@PathVariable Long id){
        Optional<TipoDIreccion> optTD = service.findById(id);
        if(optTD.isPresent()){
            return ResponseEntity.ok(optTD.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoDIreccion> delete(@PathVariable Long id){
        TipoDIreccion tipoDireccion = new TipoDIreccion();
        tipoDireccion.setId(id);
        Optional<TipoDIreccion> TPDOpt = service.deleteById(id);
        if (TPDOpt.isPresent()) {
            return ResponseEntity.ok(TPDOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<TipoDIreccion> listTipoDIreccion() {
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
