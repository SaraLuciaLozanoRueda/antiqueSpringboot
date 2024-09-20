package com.pushup.antique.empleado.controller;

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

import com.pushup.antique.empleado.application.EmpleadoImpl;
import com.pushup.antique.empleado.domain.model.Empleado;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoImpl service;

    @PostMapping
    public ResponseEntity<?> createEmpleado(@Valid @RequestBody Empleado empleado,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@Valid @RequestBody Empleado empleado,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Empleado> empleadoOpt = service.update(id, empleado);
        if (empleadoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> view(@PathVariable Long id){
        Optional<Empleado> optEmpleado = service.findById(id);
        if(optEmpleado.isPresent()){
            return ResponseEntity.ok(optEmpleado.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable Long id){
        Empleado empleado = new Empleado();
        empleado.setId(id);
        Optional<Empleado> empleadoOpt = service.deleteById(id);
        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Empleado> listEmpleado() {
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
