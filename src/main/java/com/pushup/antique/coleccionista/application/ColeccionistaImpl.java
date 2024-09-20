package com.pushup.antique.coleccionista.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.coleccionista.domain.model.Coleccionista;
import com.pushup.antique.coleccionista.infrastructure.ColeccionistaRepository;

@Service
public class ColeccionistaImpl  implements ColeccionistaService{
    @Autowired
    private ColeccionistaRepository repository;

    @Transactional
    @Override
    public Coleccionista save(Coleccionista coleccionista){
        return repository.save(coleccionista);
    }

    @Override
    public Optional<Coleccionista> update(Long id, Coleccionista coleccionista) {
        Optional<Coleccionista> coleccionistaOpt = repository.findById(id);
        if (coleccionistaOpt.isPresent()) {
            Coleccionista coleccionistaItem = coleccionistaOpt.orElseThrow();
            
            return Optional.of(repository.save(coleccionistaItem));
        }
        return coleccionistaOpt;
    }

    @Override
    public Optional<Coleccionista> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Coleccionista> deleteById(Long id) {
        Optional<Coleccionista> coleccionistaOpt = repository.findById(id);
        coleccionistaOpt.ifPresent(coleccionistaItem -> {
            repository.delete(coleccionistaItem);
        });
        return coleccionistaOpt;
    }

    @Override
    public List<Coleccionista> findAll() {
        return (List<Coleccionista>) repository.findAll();
    }
}
