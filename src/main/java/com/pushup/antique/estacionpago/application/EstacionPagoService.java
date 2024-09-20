package com.pushup.antique.estacionpago.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.estacionpago.domain.model.EstacionPago;


public interface EstacionPagoService {
    EstacionPago save(EstacionPago estacionPago);
    Optional<EstacionPago> update(Long id, EstacionPago estacionPago);
    Optional<EstacionPago> findById(Long id);
    Optional<EstacionPago> deleteById(Long id);
    List<EstacionPago> findAll();
}
