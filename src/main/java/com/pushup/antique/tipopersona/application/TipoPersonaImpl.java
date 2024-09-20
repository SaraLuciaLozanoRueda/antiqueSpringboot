package com.pushup.antique.tipopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.tipopersona.domain.model.TipoPersona;
import com.pushup.antique.tipopersona.infrastructure.TipoPersonaRepository;

@Service
public class TipoPersonaImpl implements TipoPersonaService {
    @Autowired
    private TipoPersonaRepository repository;

    @Transactional
    @Override
    public TipoPersona save(TipoPersona tipoPersona){
        return repository.save(tipoPersona);
    }

    @Override
    public Optional<TipoPersona> update(Long id, TipoPersona tipoPersona) {
        Optional<TipoPersona> TPOpt = repository.findById(id);
        if (TPOpt.isPresent()) {
            TipoPersona TPItem = TPOpt.orElseThrow();
            TPItem.setNombre(tipoPersona.getNombre());
            return Optional.of(repository.save(TPItem));
        }
        return TPOpt;
    }

    @Override
    public Optional<TipoPersona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<TipoPersona> deleteById(Long id) {
        Optional<TipoPersona> TPOpt = repository.findById(id);
        TPOpt.ifPresent(TPItem -> {
            repository.delete(TPItem);
        });
        return TPOpt;
    }

    @Override
    public List<TipoPersona> findAll() {
        return (List<TipoPersona>) repository.findAll();
    }
}
