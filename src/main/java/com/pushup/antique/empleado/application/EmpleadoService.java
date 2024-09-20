package com.pushup.antique.empleado.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.empleado.domain.model.Empleado;


public interface EmpleadoService {
    Empleado save(Empleado empleado);
    Optional<Empleado> update(Long id, Empleado empleado);
    Optional<Empleado> findById(Long id);
    Optional<Empleado> deleteById(Long id);
    List<Empleado> findAll();
}
