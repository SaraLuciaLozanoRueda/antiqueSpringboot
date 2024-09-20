package com.pushup.antique.rankingantiguedad.controller;

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

import com.pushup.antique.rankingantiguedad.application.RankingAntiguedadImpl;
import com.pushup.antique.rankingantiguedad.domain.model.RankingAntiguedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ranking")
public class RankingAntiguedadController  {
    @Autowired
    private RankingAntiguedadImpl service;

    @PostMapping
    public ResponseEntity<?> createRankingAntiguedad(@Valid @RequestBody RankingAntiguedad ranking,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ranking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRankingAntiguedad(@Valid @RequestBody RankingAntiguedad ranking,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<RankingAntiguedad> rankingOpt = service.update(id, ranking);
        if (rankingOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rankingOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankingAntiguedad> view(@PathVariable Long id){
        Optional<RankingAntiguedad> optRanking = service.findById(id);
        if(optRanking.isPresent()){
            return ResponseEntity.ok(optRanking.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RankingAntiguedad> delete(@PathVariable Long id){
        RankingAntiguedad ranking = new RankingAntiguedad();
        ranking.setId(id);
        Optional<RankingAntiguedad> rankingOpt = service.deleteById(id);
        if (rankingOpt.isPresent()) {
            return ResponseEntity.ok(rankingOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    public List<RankingAntiguedad> listRankingAntiguedad() {
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
