package com.pushup.antique.estadopersona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.estadopersona.domain.model.EstadoPersona;


public interface EstadoPersonaService {
    EstadoPersona save(EstadoPersona estadoPersona);
    Optional<EstadoPersona> update(Long id, EstadoPersona estadoPersona);
    Optional<EstadoPersona> findById(Long id);
    Optional<EstadoPersona> deleteById(Long id);
    List<EstadoPersona> findAll();
}
