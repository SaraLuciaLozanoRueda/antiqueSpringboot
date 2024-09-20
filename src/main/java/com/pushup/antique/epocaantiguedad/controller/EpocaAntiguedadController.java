package com.pushup.antique.epocaantiguedad.controller;

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

import com.pushup.antique.epocaantiguedad.application.EpocaAntiguedadImpl;
import com.pushup.antique.epocaantiguedad.domain.model.EpocaAntiguedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/epocas")
public class EpocaAntiguedadController {
    @Autowired
    private EpocaAntiguedadImpl service;

    @PostMapping
    public ResponseEntity<?> createEpocaAntiguedad(@Valid @RequestBody EpocaAntiguedad epocaAntiguedad,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(epocaAntiguedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpocaAntiguedad(@Valid @RequestBody EpocaAntiguedad epocaAntiguedad,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<EpocaAntiguedad> epocaAntiguedadOpt = service.update(id, epocaAntiguedad);
        if (epocaAntiguedadOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(epocaAntiguedadOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpocaAntiguedad> view(@PathVariable Long id){
        Optional<EpocaAntiguedad> optEpocaAntiguedad = service.findById(id);
        if(optEpocaAntiguedad.isPresent()){
            return ResponseEntity.ok(optEpocaAntiguedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EpocaAntiguedad> delete(@PathVariable Long id){
        EpocaAntiguedad epocaAntiguedad = new EpocaAntiguedad();
        epocaAntiguedad.setId(id);
        Optional<EpocaAntiguedad> epocaAntiguedadOpt = service.deleteById(id);
        if (epocaAntiguedadOpt.isPresent()) {
            return ResponseEntity.ok(epocaAntiguedadOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<EpocaAntiguedad> listEpocaAntiguedad() {
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
