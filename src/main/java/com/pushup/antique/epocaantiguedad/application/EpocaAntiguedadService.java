package com.pushup.antique.epocaantiguedad.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.epocaantiguedad.domain.model.EpocaAntiguedad;


public interface EpocaAntiguedadService {
    EpocaAntiguedad save(EpocaAntiguedad epocaAntiguedad);
    Optional<EpocaAntiguedad> update(Long id, EpocaAntiguedad epocaAntiguedad);
    Optional<EpocaAntiguedad> findById(Long id);
    Optional<EpocaAntiguedad> deleteById(Long id);
    List<EpocaAntiguedad> findAll();
}
