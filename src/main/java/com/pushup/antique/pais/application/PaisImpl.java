package com.pushup.antique.pais.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.pais.domain.model.Pais;
import com.pushup.antique.pais.infrastructure.PaisRepository;

@Service
public class PaisImpl implements PaisService {
    @Autowired
    private PaisRepository repository;

    @Transactional
    @Override
    public Pais save(Pais pais){
        return repository.save(pais);
    }

    @Override
    public Optional<Pais> update(Long id, Pais pais) {
        Optional<Pais> paisOpt = repository.findById(id);
        if (paisOpt.isPresent()) {
            Pais paisItem = paisOpt.orElseThrow();
            paisItem.setNombre(pais.getNombre());
            return Optional.of(repository.save(paisItem));
        }
        return paisOpt;
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Pais> deleteById(Long id) {
        Optional<Pais> paisOpt = repository.findById(id);
        paisOpt.ifPresent(paisItem -> {
            repository.delete(paisItem);
        });
        return paisOpt;
    }

    @Override
    public List<Pais> findAll() {
        return (List<Pais>) repository.findAll();
    }
}
