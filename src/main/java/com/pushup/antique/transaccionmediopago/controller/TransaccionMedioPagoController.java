package com.pushup.antique.transaccionmediopago.controller;

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

import com.pushup.antique.transaccionmediopago.application.TransaccionMedioPagoImpl;
import com.pushup.antique.transaccionmediopago.domain.model.TransaccionMedioPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaccionmediopago")
public class TransaccionMedioPagoController {
    @Autowired
    private TransaccionMedioPagoImpl service;

    @PostMapping
    public ResponseEntity<?> createTipMovCaja(@Valid @RequestBody TransaccionMedioPago TMP,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(TMP));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipMovCaja(@Valid @RequestBody TransaccionMedioPago TMP,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TransaccionMedioPago> tMPOpt = service.update(id, TMP);
        if (tMPOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tMPOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionMedioPago> view(@PathVariable Long id){
        Optional<TransaccionMedioPago> optTMP = service.findById(id);
        if(optTMP.isPresent()){
            return ResponseEntity.ok(optTMP.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransaccionMedioPago> delete(@PathVariable Long id){
        TransaccionMedioPago TMP = new TransaccionMedioPago();
        TMP.setId(id);
        Optional<TransaccionMedioPago> tMPOpt = service.deleteById(id);
        if (tMPOpt.isPresent()) {
            return ResponseEntity.ok(tMPOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<TransaccionMedioPago> listTipMovCaja() {
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
