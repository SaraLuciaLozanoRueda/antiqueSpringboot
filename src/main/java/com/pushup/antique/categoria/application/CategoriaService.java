package com.pushup.antique.categoria.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.categoria.domain.model.Categoria;


public interface CategoriaService {
    Categoria save(Categoria antiguedad);
    Optional<Categoria> update(Long id, Categoria antiguedad);
    Optional<Categoria> findById(Long id);
    Optional<Categoria> deleteById(Long id);
    List<Categoria> findAll();
}
