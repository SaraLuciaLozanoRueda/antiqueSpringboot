package com.pushup.antique.clasecontacto.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.clasecontacto.domain.model.ClaseContacto;


public interface ClaseContactoService {
    ClaseContacto save(ClaseContacto claseContacto);
    Optional<ClaseContacto> update(Long id, ClaseContacto claseContacto);
    Optional<ClaseContacto> findById(Long id);
    Optional<ClaseContacto> deleteById(Long id);
    List<ClaseContacto> findAll();
}
