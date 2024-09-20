package com.pushup.antique.antiguedades.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;

public interface AntiguedadService {
    Antiguedad save(Antiguedad antiguedad);
    Optional<Antiguedad> update(Long id, Antiguedad antiguedad);
    Optional<Antiguedad> findById(Long id);
    Optional<Antiguedad> deleteById(Long id);
    List<Antiguedad> findAll();
}
