package com.pushup.antique.estadopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.estadopersona.domain.model.EstadoPersona;
import com.pushup.antique.estadopersona.infrastrucure.EstadoPersonaRepository;
@Service
public class EstadoPersonaImpl implements EstadoPersonaService {
    @Autowired
    private EstadoPersonaRepository repository;

    @Transactional
    @Override
    public EstadoPersona save(EstadoPersona estadoPersona){
        return repository.save(estadoPersona);
    }

    @Override
    public Optional<EstadoPersona> update(Long id, EstadoPersona estadoPersona) {
        Optional<EstadoPersona> estadoPersonaOpt = repository.findById(id);
        if (estadoPersonaOpt.isPresent()) {
            EstadoPersona estadoPersonaItem = estadoPersonaOpt.orElseThrow();
            estadoPersonaItem.setNombre(estadoPersona.getNombre());
            return Optional.of(repository.save(estadoPersonaItem));
        }
        return estadoPersonaOpt;
    }

    @Override
    public Optional<EstadoPersona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<EstadoPersona> deleteById(Long id) {
        Optional<EstadoPersona> estadoPersonaOpt = repository.findById(id);
        estadoPersonaOpt.ifPresent(estadoPersonaItem -> {
            repository.delete(estadoPersonaItem);
        });
        return estadoPersonaOpt;
    }

    @Override
    public List<EstadoPersona> findAll() {
        return (List<EstadoPersona>) repository.findAll();
    }
}
