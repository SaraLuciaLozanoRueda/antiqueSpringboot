package com.pushup.antique.ciudad.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.ciudad.domain.model.Ciudad;


public interface CiudadService {    
    Ciudad save(Ciudad ciudad);
    Optional<Ciudad> update(Long id, Ciudad ciudad);
    Optional<Ciudad> findById(Long id);
    Optional<Ciudad> deleteById(Long id);
    List<Ciudad> findAll();
}
