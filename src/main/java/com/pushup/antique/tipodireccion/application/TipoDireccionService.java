package com.pushup.antique.tipodireccion.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.tipodireccion.domain.model.TipoDIreccion;


public interface TipoDireccionService {
    TipoDIreccion save(TipoDIreccion tipoDireccion);
    Optional<TipoDIreccion> update(Long id, TipoDIreccion tipoDireccion);
    Optional<TipoDIreccion> findById(Long id);
    Optional<TipoDIreccion> deleteById(Long id);
    List<TipoDIreccion> findAll();
}
