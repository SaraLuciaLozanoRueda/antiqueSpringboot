package com.pushup.antique.tipotransaccion.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.tipotransaccion.domain.models.TipoTransaccion;

public interface TipoTransaccionService {
    TipoTransaccion save(TipoTransaccion tipoTransaccion);
    Optional<TipoTransaccion> update(Long id, TipoTransaccion tipoTransaccion);
    Optional<TipoTransaccion> findById(Long id);
    Optional<TipoTransaccion> deleteById(Long id);
    List<TipoTransaccion> findAll();
}
