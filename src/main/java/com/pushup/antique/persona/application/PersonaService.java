package com.pushup.antique.persona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.persona.domain.model.Persona;

public interface PersonaService {
    Persona save(Persona persona);
    Optional<Persona> update(Long id, Persona persona);
    Optional<Persona> findById(Long id);
    Optional<Persona> deleteById(Long id);
    List<Persona> findAll();
}
