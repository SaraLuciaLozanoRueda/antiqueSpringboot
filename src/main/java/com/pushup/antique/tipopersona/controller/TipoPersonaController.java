package com.pushup.antique.tipopersona.controller;

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

import com.pushup.antique.tipopersona.application.TipoPersonaImpl;
import com.pushup.antique.tipopersona.domain.model.TipoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipopersona")
public class TipoPersonaController {
    @Autowired
    private TipoPersonaImpl service;

    @PostMapping
    public ResponseEntity<?> createTipMovCaja(@Valid @RequestBody TipoPersona tipoPersona,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tipoPersona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipMovCaja(@Valid @RequestBody TipoPersona tipoPersona,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TipoPersona> TPOPt = service.update(id, tipoPersona);
        if (TPOPt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TPOPt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPersona> view(@PathVariable Long id){
        Optional<TipoPersona> optTP = service.findById(id);
        if(optTP.isPresent()){
            return ResponseEntity.ok(optTP.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoPersona> delete(@PathVariable Long id){
        TipoPersona tipoPersona = new TipoPersona();
        tipoPersona.setId(id);
        Optional<TipoPersona> TPOPt = service.deleteById(id);
        if (TPOPt.isPresent()) {
            return ResponseEntity.ok(TPOPt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<TipoPersona> listTipMovCaja() {
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
