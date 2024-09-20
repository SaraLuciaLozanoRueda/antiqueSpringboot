package com.pushup.antique.galeria.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.galeria.domain.model.Galeria;
import com.pushup.antique.galeria.infrastructure.GaleriaRepository;

@Service
public class GaleriaImpl implements GaleriaService{
    @Autowired
    private GaleriaRepository repository;

    @Transactional
    @Override
    public Galeria save(Galeria galeria){
        return repository.save(galeria);
    }

    @Override
    public Optional<Galeria> update(Long id, Galeria galeria) {
        Optional<Galeria> galeriaOpt = repository.findById(id);
        if (galeriaOpt.isPresent()) {
            Galeria galeriaItem = galeriaOpt.orElseThrow();
            galeriaItem.setUrl(galeria.getUrl());
            return Optional.of(repository.save(galeriaItem));
        }
        return galeriaOpt;
    }

    @Override
    public Optional<Galeria> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Galeria> deleteById(Long id) {
        Optional<Galeria> galeriaOpt = repository.findById(id);
        galeriaOpt.ifPresent(galeriaItem -> {
            repository.delete(galeriaItem);
        });
        return galeriaOpt;
    }

    @Override
    public List<Galeria> findAll() {
        return (List<Galeria>) repository.findAll();
    }
}
