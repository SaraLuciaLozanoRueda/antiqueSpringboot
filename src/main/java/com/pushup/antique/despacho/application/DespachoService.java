package com.pushup.antique.despacho.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.despacho.domain.model.Despacho;


public interface DespachoService {
    Despacho save(Despacho despacho);
    Optional<Despacho> update(Long id, Despacho despacho);
    Optional<Despacho> findById(Long id);
    Optional<Despacho> deleteById(Long id);
    List<Despacho> findAll();

}
