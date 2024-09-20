package com.pushup.antique.region.controller;

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

import com.pushup.antique.region.application.RegionImpl;
import com.pushup.antique.region.domain.model.Region;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regiones")
public class RegionController {
    @Autowired
    private RegionImpl service;

    @PostMapping
    public ResponseEntity<?> createRegion(@Valid @RequestBody Region region,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@Valid @RequestBody Region region,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Region> regionOpt = service.update(id, region);
        if (regionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(regionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> view(@PathVariable Long id){
        Optional<Region> optRegion = service.findById(id);
        if(optRegion.isPresent()){
            return ResponseEntity.ok(optRegion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> delete(@PathVariable Long id){
        Region region = new Region();
        region.setId(id);
        Optional<Region> regionOpt = service.deleteById(id);
        if (regionOpt.isPresent()) {
            return ResponseEntity.ok(regionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<Region> listRegion() {
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
