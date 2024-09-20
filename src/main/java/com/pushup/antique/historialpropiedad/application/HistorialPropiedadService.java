package com.pushup.antique.historialpropiedad.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.historialpropiedad.domain.model.HistorialPropiedad;

public interface HistorialPropiedadService {
    HistorialPropiedad save(HistorialPropiedad historial);
    Optional<HistorialPropiedad> update(Long id, HistorialPropiedad historial);
    Optional<HistorialPropiedad> findById(Long id);
    Optional<HistorialPropiedad> deleteById(Long id);
    List<HistorialPropiedad> findAll();
}
