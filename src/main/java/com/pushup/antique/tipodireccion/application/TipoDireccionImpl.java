package com.pushup.antique.tipodireccion.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.tipodireccion.domain.model.TipoDIreccion;
import com.pushup.antique.tipodireccion.infrastructure.TipoDireccionRepository;

@Service
public class TipoDireccionImpl implements TipoDireccionService{
    @Autowired
    private TipoDireccionRepository repository;
    
    @Transactional
    @Override
    public TipoDIreccion save(TipoDIreccion tipoDireccion){
        return repository.save(tipoDireccion);
    }

    @Override
    public Optional<TipoDIreccion> update(Long id, TipoDIreccion tipoDireccion) {
        Optional<TipoDIreccion> TDOpt = repository.findById(id);
        if (TDOpt.isPresent()) {
            TipoDIreccion TDItem = TDOpt.orElseThrow();
            TDItem.setNombre(tipoDireccion.getNombre());
            return Optional.of(repository.save(TDItem));
        }
        return TDOpt;
    }

    @Override
    public Optional<TipoDIreccion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<TipoDIreccion> deleteById(Long id) {
        Optional<TipoDIreccion> TDOpt = repository.findById(id);
        TDOpt.ifPresent(TDItem -> {
            repository.delete(TDItem);
        });
        return TDOpt;
    }

    @Override
    public List<TipoDIreccion> findAll() {
        return (List<TipoDIreccion>) repository.findAll();
    }

}
