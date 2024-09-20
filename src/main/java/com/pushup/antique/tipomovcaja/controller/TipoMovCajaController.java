package com.pushup.antique.tipomovcaja.controller;

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

import com.pushup.antique.tipomovcaja.application.TipoMovCajaImpl;
import com.pushup.antique.tipomovcaja.domain.model.TipMovCaja;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipomovcaja")
public class TipoMovCajaController {
    @Autowired
    private TipoMovCajaImpl service;

    @PostMapping
    public ResponseEntity<?> createTipMovCaja(@Valid @RequestBody TipMovCaja tMovCaja,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tMovCaja));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipMovCaja(@Valid @RequestBody TipMovCaja tMovCaja,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TipMovCaja> TMCOpt = service.update(id, tMovCaja);
        if (TMCOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TMCOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipMovCaja> view(@PathVariable Long id){
        Optional<TipMovCaja> optTMC = service.findById(id);
        if(optTMC.isPresent()){
            return ResponseEntity.ok(optTMC.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipMovCaja> delete(@PathVariable Long id){
        TipMovCaja tMovCaja = new TipMovCaja();
        tMovCaja.setId(id);
        Optional<TipMovCaja> TMCOpt = service.deleteById(id);
        if (TMCOpt.isPresent()) {
            return ResponseEntity.ok(TMCOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<TipMovCaja> listTipMovCaja() {
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
