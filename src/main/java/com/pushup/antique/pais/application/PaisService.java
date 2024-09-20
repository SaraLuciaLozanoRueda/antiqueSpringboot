package com.pushup.antique.pais.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.pais.domain.model.Pais;

public interface PaisService {
    Pais save(Pais pais);
    Optional<Pais> update(Long id, Pais pais);
    Optional<Pais> findById(Long id);
    Optional<Pais> deleteById(Long id);
    List<Pais> findAll();
}
