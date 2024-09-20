package com.pushup.antique.transaccion.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.transaccion.domain.model.Transaccion;


public interface TransaccionService {
    Transaccion save(Transaccion transaccion);
    Optional<Transaccion> update(Long id, Transaccion transaccion);
    Optional<Transaccion> findById(Long id);
    Optional<Transaccion> deleteById(Long id);
    List<Transaccion> findAll();
}
