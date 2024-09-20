package com.pushup.antique.coleccionista.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.coleccionista.domain.model.Coleccionista;



public interface ColeccionistaService {
    Coleccionista save(Coleccionista coleccionista);
    Optional<Coleccionista> update(Long id, Coleccionista coleccionista);
    Optional<Coleccionista> findById(Long id);
    Optional<Coleccionista> deleteById(Long id);
    List<Coleccionista> findAll();
}
