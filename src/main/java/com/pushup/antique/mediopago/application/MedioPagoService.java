package com.pushup.antique.mediopago.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.mediopago.domain.model.MedioPago;


public interface MedioPagoService {
    MedioPago save(MedioPago medioPago);
    Optional<MedioPago> update(Long id, MedioPago medioPago);
    Optional<MedioPago> findById(Long id);
    Optional<MedioPago> deleteById(Long id);
    List<MedioPago> findAll();
}
