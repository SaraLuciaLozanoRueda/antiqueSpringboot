package com.pushup.antique.historialpropiedad.controller;

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

import com.pushup.antique.historialpropiedad.application.HistorialPropiedadImpl;
import com.pushup.antique.historialpropiedad.domain.model.HistorialPropiedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/historial")
public class HistorialPropiedadCOntroller {
    @Autowired
    private HistorialPropiedadImpl service;

    @PostMapping
    public ResponseEntity<?> createHistorial(@Valid @RequestBody HistorialPropiedad historial,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(historial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHistorial(@Valid @RequestBody HistorialPropiedad historial,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<HistorialPropiedad> historialOpt = service.update(id, historial);
        if (historialOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(historialOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialPropiedad> view(@PathVariable Long id){
        Optional<HistorialPropiedad> optHistorial = service.findById(id);
        if(optHistorial.isPresent()){
            return ResponseEntity.ok(optHistorial.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HistorialPropiedad> delete(@PathVariable Long id){
        HistorialPropiedad historial = new HistorialPropiedad();
        historial.setId(id);
        Optional<HistorialPropiedad> historialOpt = service.deleteById(id);
        if (historialOpt.isPresent()) {
            return ResponseEntity.ok(historialOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<HistorialPropiedad> listHistorial() {
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
