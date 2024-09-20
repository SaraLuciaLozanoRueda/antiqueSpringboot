package com.pushup.antique.historialpropiedad.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.historialpropiedad.domain.model.HistorialPropiedad;
import com.pushup.antique.historialpropiedad.infrastructure.HistorialPropiedadRepository;

@Service
public class HistorialPropiedadImpl implements HistorialPropiedadService {
    @Autowired
    private HistorialPropiedadRepository repository;
    
    @Transactional
    @Override
    public HistorialPropiedad save(HistorialPropiedad historial){
        return repository.save(historial);
    }

    @Override
    public Optional<HistorialPropiedad> update(Long id, HistorialPropiedad historial) {
        Optional<HistorialPropiedad> historialOpt = repository.findById(id);
        if (historialOpt.isPresent()) {
            HistorialPropiedad historialItem = historialOpt.orElseThrow();
            historialItem.setPersona(historial.getPersona());
            historialItem.setAntiguedad(historial.getAntiguedad());
            return Optional.of(repository.save(historialItem));
        }
        return historialOpt;
    }

    @Override
    public Optional<HistorialPropiedad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<HistorialPropiedad> deleteById(Long id) {
        Optional<HistorialPropiedad> historialOpt = repository.findById(id);
        historialOpt.ifPresent(historialItem -> {
            repository.delete(historialItem);
        });
        return historialOpt;
    }

    @Override
    public List<HistorialPropiedad> findAll() {
        return (List<HistorialPropiedad>) repository.findAll();
    }

}
