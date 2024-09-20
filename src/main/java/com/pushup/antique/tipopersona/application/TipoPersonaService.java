package com.pushup.antique.tipopersona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.tipopersona.domain.model.TipoPersona;

public interface TipoPersonaService {
    TipoPersona save(TipoPersona tipoPersona);
    Optional<TipoPersona> update(Long id, TipoPersona tipoPersona);
    Optional<TipoPersona> findById(Long id);
    Optional<TipoPersona> deleteById(Long id);
    List<TipoPersona> findAll();
}
