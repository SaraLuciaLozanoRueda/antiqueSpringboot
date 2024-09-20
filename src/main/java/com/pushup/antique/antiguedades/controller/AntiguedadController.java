package com.pushup.antique.antiguedades.controller;

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

import com.pushup.antique.antiguedades.application.AntiguedadImpl;
import com.pushup.antique.antiguedades.domain.model.Antiguedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/antiguedades")
public class AntiguedadController {
    @Autowired
    private AntiguedadImpl service;

    @PostMapping
    public ResponseEntity<?> createAntiguedad(@Valid @RequestBody Antiguedad antiguedad,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(antiguedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAntiguedad(@Valid @RequestBody Antiguedad antiguedad,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Antiguedad> antiguedadOpt = service.update(id, antiguedad);
        if (antiguedadOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(antiguedadOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antiguedad> view(@PathVariable Long id){
        Optional<Antiguedad> optionalAntiguedad = service.findById(id);
        if(optionalAntiguedad.isPresent()){
            return ResponseEntity.ok(optionalAntiguedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Antiguedad> delete(@PathVariable Long id){
        Antiguedad antiguedad = new Antiguedad();
        antiguedad.setId(id);
        Optional<Antiguedad> antigueadOptional = service.deleteById(id);
        if (antigueadOptional.isPresent()) {
            return ResponseEntity.ok(antigueadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Antiguedad> listAntiguedad() {
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
