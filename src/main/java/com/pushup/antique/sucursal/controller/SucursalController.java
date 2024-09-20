package com.pushup.antique.sucursal.controller;

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

import com.pushup.antique.sucursal.domain.model.Sucursal;
import com.pushup.antique.sucursal.application.SucursalImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalImpl service;

    @PostMapping
    public ResponseEntity<?> createSucursal(@Valid @RequestBody Sucursal sucursal,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSucursal(@Valid @RequestBody Sucursal sucursal,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Sucursal> sucursalOpt = service.update(id, sucursal);
        if (sucursalOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sucursalOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> view(@PathVariable Long id){
        Optional<Sucursal> optSucursal = service.findById(id);
        if(optSucursal.isPresent()){
            return ResponseEntity.ok(optSucursal.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sucursal> delete(@PathVariable Long id){
        Sucursal sucursal = new Sucursal();
        sucursal.setId(id);
        Optional<Sucursal> sucursalOpt = service.deleteById(id);
        if (sucursalOpt.isPresent()) {
            return ResponseEntity.ok(sucursalOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Sucursal> listSucursal() {
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
