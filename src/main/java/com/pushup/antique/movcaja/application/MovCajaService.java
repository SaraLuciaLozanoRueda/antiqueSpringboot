package com.pushup.antique.movcaja.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.movcaja.domain.model.MovCaja;


public interface MovCajaService {
    MovCaja save(MovCaja movCaja);
    Optional<MovCaja> update(Long id, MovCaja movCaja);
    Optional<MovCaja> findById(Long id);
    Optional<MovCaja> deleteById(Long id);
    List<MovCaja> findAll();
}
