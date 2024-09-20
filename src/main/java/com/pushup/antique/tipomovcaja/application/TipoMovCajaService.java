package com.pushup.antique.tipomovcaja.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.tipomovcaja.domain.model.TipMovCaja;


public interface TipoMovCajaService {
    TipMovCaja save(TipMovCaja tMovCaja);
    Optional<TipMovCaja> update(Long id, TipMovCaja tMovCaja);
    Optional<TipMovCaja> findById(Long id);
    Optional<TipMovCaja> deleteById(Long id);
    List<TipMovCaja> findAll();
}
