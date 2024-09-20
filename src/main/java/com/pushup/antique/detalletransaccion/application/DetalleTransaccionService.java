package com.pushup.antique.detalletransaccion.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.detalletransaccion.domain.model.DetalleTransaccion;


public interface DetalleTransaccionService {
    DetalleTransaccion save(DetalleTransaccion detalleTransaccion);
    Optional<DetalleTransaccion> update(Long id, DetalleTransaccion detalleTransaccion);
    Optional<DetalleTransaccion> findById(Long id);
    Optional<DetalleTransaccion> deleteById(Long id);
    List<DetalleTransaccion> findAll();
}
