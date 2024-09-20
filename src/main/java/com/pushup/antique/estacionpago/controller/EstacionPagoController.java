package com.pushup.antique.estacionpago.controller;

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

import com.pushup.antique.estacionpago.application.EstacionPagoImpl;
import com.pushup.antique.estacionpago.domain.model.EstacionPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estacionpago")
public class EstacionPagoController {
    @Autowired
    private EstacionPagoImpl service;

    @PostMapping
    public ResponseEntity<?> createEstacionPago(@Valid @RequestBody EstacionPago estacionPago,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estacionPago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstacionPago(@Valid @RequestBody EstacionPago estacionPago,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<EstacionPago> estacionPagoOpt = service.update(id, estacionPago);
        if (estacionPagoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionPagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionPago> view(@PathVariable Long id){
        Optional<EstacionPago> optEstacionPago = service.findById(id);
        if(optEstacionPago.isPresent()){
            return ResponseEntity.ok(optEstacionPago.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstacionPago> delete(@PathVariable Long id){
        EstacionPago estacionPago = new EstacionPago();
        estacionPago.setId(id);
        Optional<EstacionPago> estacionPagoOpt = service.deleteById(id);
        if (estacionPagoOpt.isPresent()) {
            return ResponseEntity.ok(estacionPagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<EstacionPago> listEstacionPago() {
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
