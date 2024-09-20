package com.pushup.antique.transaccion.controller;

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

import com.pushup.antique.transaccion.application.TransaccionImpl;
import com.pushup.antique.transaccion.domain.model.Transaccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {
    @Autowired
    private TransaccionImpl service;

    @PostMapping
    public ResponseEntity<?> createTipMovCaja(@Valid @RequestBody Transaccion transaccion,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transaccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipMovCaja(@Valid @RequestBody Transaccion transaccion,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Transaccion> transaccionOpt = service.update(id, transaccion);
        if (transaccionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transaccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> view(@PathVariable Long id){
        Optional<Transaccion> optTransaccion = service.findById(id);
        if(optTransaccion.isPresent()){
            return ResponseEntity.ok(optTransaccion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaccion> delete(@PathVariable Long id){
        Transaccion transaccion = new Transaccion();
        transaccion.setId(id);
        Optional<Transaccion> transaccionOpt = service.deleteById(id);
        if (transaccionOpt.isPresent()) {
            return ResponseEntity.ok(transaccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Transaccion> listTipMovCaja() {
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
