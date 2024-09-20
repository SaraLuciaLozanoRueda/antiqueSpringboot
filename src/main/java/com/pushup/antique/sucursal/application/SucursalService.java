package com.pushup.antique.sucursal.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.sucursal.domain.model.Sucursal;


public interface SucursalService {
    Sucursal save(Sucursal sucursal);
    Optional<Sucursal> update(Long id, Sucursal sucursal);
    Optional<Sucursal> findById(Long id);
    Optional<Sucursal> deleteById(Long id);
    List<Sucursal> findAll();
}
