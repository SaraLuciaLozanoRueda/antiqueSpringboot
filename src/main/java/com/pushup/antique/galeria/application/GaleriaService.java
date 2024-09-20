package com.pushup.antique.galeria.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.galeria.domain.model.Galeria;

public interface GaleriaService {
    Galeria save(Galeria galeria);
    Optional<Galeria> update(Long id, Galeria galeria);
    Optional<Galeria> findById(Long id);
    Optional<Galeria> deleteById(Long id);
    List<Galeria> findAll();
}
