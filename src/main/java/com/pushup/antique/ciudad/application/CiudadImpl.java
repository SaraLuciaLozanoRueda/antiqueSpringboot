package com.pushup.antique.ciudad.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.ciudad.domain.model.Ciudad;
import com.pushup.antique.ciudad.infrastructure.CiudadRepository;

@Service
public class CiudadImpl implements CiudadService {
    @Autowired
    private CiudadRepository repository;

    @Transactional
    @Override
    public Ciudad save(Ciudad ciudad){
        return repository.save(ciudad);
    }

    @Override
    public Optional<Ciudad> update(Long id, Ciudad ciudad) {
        Optional<Ciudad> ciudadOpt = repository.findById(id);
        if (ciudadOpt.isPresent()) {
            Ciudad ciudadItem = ciudadOpt.orElseThrow();
            ciudadItem.setNombre(ciudad.getNombre());
            ciudadItem.setRegion(ciudad.getRegion());
            return Optional.of(repository.save(ciudadItem));
        }
        return ciudadOpt;
    }

    @Override
    public Optional<Ciudad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Ciudad> deleteById(Long id) {
        Optional<Ciudad> ciudadOpt = repository.findById(id);
        ciudadOpt.ifPresent(ciudadItem -> {
            repository.delete(ciudadItem);
        });
        return ciudadOpt;
    }

    @Override
    public List<Ciudad> findAll() {
        return (List<Ciudad>) repository.findAll();
    }

}
