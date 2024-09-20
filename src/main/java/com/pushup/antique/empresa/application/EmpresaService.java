package com.pushup.antique.empresa.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.empresa.domain.model.Empresa;

public interface EmpresaService {
    Empresa save(Empresa empresa);
    Optional<Empresa> update(Long id, Empresa empresa);
    Optional<Empresa> findById(Long id);
    Optional<Empresa> deleteById(Long id);
    List<Empresa> findAll();
}
