package com.pushup.antique.empresa.controller;

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

import com.pushup.antique.empresa.application.EmpresaImpl;
import com.pushup.antique.empresa.domain.model.Empresa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaImpl service;

    @PostMapping
    public ResponseEntity<?> createEmpresa(@Valid @RequestBody Empresa empresa,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(empresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpresa(@Valid @RequestBody Empresa empresa,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Empresa> empresaOpt = service.update(id, empresa);
        if (empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> view(@PathVariable Long id){
        Optional<Empresa> optEmpresa = service.findById(id);
        if(optEmpresa.isPresent()){
            return ResponseEntity.ok(optEmpresa.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> delete(@PathVariable Long id){
        Empresa empresa = new Empresa();
        empresa.setId(id);
        Optional<Empresa> empresaOpt = service.deleteById(id);
        if (empresaOpt.isPresent()) {
            return ResponseEntity.ok(empresaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Empresa> listEmpresa() {
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
