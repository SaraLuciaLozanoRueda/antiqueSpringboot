package com.pushup.antique.direccionpersona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.direccionpersona.domain.model.DIreccionPersona;


public interface DireccionPersonaService {
    DIreccionPersona save(DIreccionPersona direccionPersona);
    Optional<DIreccionPersona> update(Long id, DIreccionPersona direccionPersona);
    Optional<DIreccionPersona> findById(Long id);
    Optional<DIreccionPersona> deleteById(Long id);
    List<DIreccionPersona> findAll();
}
