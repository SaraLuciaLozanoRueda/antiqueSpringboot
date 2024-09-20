package com.pushup.antique.tipotransaccion.controller;

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

import com.pushup.antique.tipotransaccion.application.TipoTransaccionImpl;
import com.pushup.antique.tipotransaccion.domain.models.TipoTransaccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipotransacciom")
public class TipoTransaccionController {
    @Autowired
    private TipoTransaccionImpl service;

    @PostMapping
    public ResponseEntity<?> createTipMovCaja(@Valid @RequestBody TipoTransaccion tipoTransaccion,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tipoTransaccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipMovCaja(@Valid @RequestBody TipoTransaccion tipoTransaccion,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TipoTransaccion> TTOpt = service.update(id, tipoTransaccion);
        if (TTOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(TTOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransaccion> view(@PathVariable Long id){
        Optional<TipoTransaccion> optTT = service.findById(id);
        if(optTT.isPresent()){
            return ResponseEntity.ok(optTT.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoTransaccion> delete(@PathVariable Long id){
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setId(id);
        Optional<TipoTransaccion> TTOpt = service.deleteById(id);
        if (TTOpt.isPresent()) {
            return ResponseEntity.ok(TTOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<TipoTransaccion> listTipMovCaja() {
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
