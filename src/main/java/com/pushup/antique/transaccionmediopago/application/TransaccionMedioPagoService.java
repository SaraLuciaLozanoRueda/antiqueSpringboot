package com.pushup.antique.transaccionmediopago.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.transaccionmediopago.domain.model.TransaccionMedioPago;


public interface TransaccionMedioPagoService {
    TransaccionMedioPago save(TransaccionMedioPago TMP);
    Optional<TransaccionMedioPago> update(Long id, TransaccionMedioPago TMP);
    Optional<TransaccionMedioPago> findById(Long id);
    Optional<TransaccionMedioPago> deleteById(Long id);
    List<TransaccionMedioPago> findAll();
}
