package com.pushup.antique.mediopago.controller;

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

import com.pushup.antique.mediopago.application.MedioPagoImpl;
import com.pushup.antique.mediopago.domain.model.MedioPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mediopago")
public class MedioPagoController {
    @Autowired
    private MedioPagoImpl service;

    @PostMapping
    public ResponseEntity<?> createMedioPago(@Valid @RequestBody MedioPago medioPago,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(medioPago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedioPago(@Valid @RequestBody MedioPago medioPago,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<MedioPago> medioPagoOpt = service.update(id, medioPago);
        if (medioPagoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medioPagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedioPago> view(@PathVariable Long id){
        Optional<MedioPago> optMedioPago = service.findById(id);
        if(optMedioPago.isPresent()){
            return ResponseEntity.ok(optMedioPago.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedioPago> delete(@PathVariable Long id){
        MedioPago medioPago = new MedioPago();
        medioPago.setId(id);
        Optional<MedioPago> medioPagoOpt = service.deleteById(id);
        if (medioPagoOpt.isPresent()) {
            return ResponseEntity.ok(medioPagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<MedioPago> listMedioPago() {
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
