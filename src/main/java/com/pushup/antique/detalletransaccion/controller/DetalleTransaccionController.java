package com.pushup.antique.detalletransaccion.controller;

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

import com.pushup.antique.detalletransaccion.application.DetalleTransaccionImpl;
import com.pushup.antique.detalletransaccion.domain.model.DetalleTransaccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/detalletransacciones")
public class DetalleTransaccionController {
    @Autowired
    private DetalleTransaccionImpl service;

    @PostMapping
    public ResponseEntity<?> createDetalleTransaccion(@Valid @RequestBody DetalleTransaccion detalleTransaccion,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(detalleTransaccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetalleTransaccion(@Valid @RequestBody DetalleTransaccion detalleTransaccion,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<DetalleTransaccion> detalleTransaccionOpt = service.update(id, detalleTransaccion);
        if (detalleTransaccionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(detalleTransaccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTransaccion> view(@PathVariable Long id){
        Optional<DetalleTransaccion> optDetalleTransaccion = service.findById(id);
        if(optDetalleTransaccion.isPresent()){
            return ResponseEntity.ok(optDetalleTransaccion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleTransaccion> delete(@PathVariable Long id){
        DetalleTransaccion detalleTransaccion = new DetalleTransaccion();
        detalleTransaccion.setId(id);
        Optional<DetalleTransaccion> detalleTransaccionOpt = service.deleteById(id);
        if (detalleTransaccionOpt.isPresent()) {
            return ResponseEntity.ok(detalleTransaccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<DetalleTransaccion> listDetalleTransaccion() {
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
