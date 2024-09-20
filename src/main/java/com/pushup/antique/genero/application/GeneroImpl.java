package com.pushup.antique.genero.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.genero.domain.model.Genero;
import com.pushup.antique.genero.infrastructure.GeneroRepository;

@Service
public class GeneroImpl implements GeneroService {
    @Autowired
    private GeneroRepository repository;

    @Transactional
    @Override
    public Genero save(Genero genero){
        return repository.save(genero);
    }

    @Override
    public Optional<Genero> update(Long id, Genero genero) {
        Optional<Genero> generoOpt = repository.findById(id);
        if (generoOpt.isPresent()) {
            Genero generoItem = generoOpt.orElseThrow();
            generoItem.setNombre(genero.getNombre());
            return Optional.of(repository.save(generoItem));
        }
        return generoOpt;
    }

    @Override
    public Optional<Genero> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Genero> deleteById(Long id) {
        Optional<Genero> generoOpt = repository.findById(id);
        generoOpt.ifPresent(generoItem -> {
            repository.delete(generoItem);
        });
        return generoOpt;
    }

    @Override
    public List<Genero> findAll() {
        return (List<Genero>) repository.findAll();
    }

}
