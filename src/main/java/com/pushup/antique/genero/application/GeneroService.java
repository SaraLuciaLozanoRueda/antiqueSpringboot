package com.pushup.antique.genero.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.genero.domain.model.Genero;

public interface GeneroService {
    Genero save(Genero genero);
    Optional<Genero> update(Long id, Genero genero);
    Optional<Genero> findById(Long id);
    Optional<Genero> deleteById(Long id);
    List<Genero> findAll();
}
