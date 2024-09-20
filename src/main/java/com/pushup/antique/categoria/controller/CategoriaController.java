package com.pushup.antique.categoria.controller;

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

import com.pushup.antique.categoria.application.CategoriaImpl;
import com.pushup.antique.categoria.domain.model.Categoria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaImpl service;

    @PostMapping
    public ResponseEntity<?> createCategoria(@Valid @RequestBody Categoria categoria,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@Valid @RequestBody Categoria categoria,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Categoria> categoriaOpt = service.update(id, categoria);
        if (categoriaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> view(@PathVariable Long id){
        Optional<Categoria> optionalAntiguedad = service.findById(id);
        if(optionalAntiguedad.isPresent()){
            return ResponseEntity.ok(optionalAntiguedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Long id){
        Categoria categoria = new Categoria();
        categoria.setId(id);
        Optional<Categoria> categoriaOptional = service.deleteById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Categoria> listCategoria() {
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
