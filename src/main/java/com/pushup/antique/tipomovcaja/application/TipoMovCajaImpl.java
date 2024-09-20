package com.pushup.antique.tipomovcaja.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.tipomovcaja.domain.model.TipMovCaja;
import com.pushup.antique.tipomovcaja.infrastructure.TipoMovCajaRepository;

@Service
public class TipoMovCajaImpl implements TipoMovCajaService {
    @Autowired 
    private 
    TipoMovCajaRepository repository;

    @Transactional
    @Override
    public TipMovCaja save(TipMovCaja tMovCaja){
        return repository.save(tMovCaja);
    }

    @Override
    public Optional<TipMovCaja> update(Long id, TipMovCaja tMovCaja) {
        Optional<TipMovCaja> TMCOpt = repository.findById(id);
        if (TMCOpt.isPresent()) {
            TipMovCaja TMCItem = TMCOpt.orElseThrow();
            TMCItem.setNombre(tMovCaja.getNombre());
            return Optional.of(repository.save(TMCItem));
        }
        return TMCOpt;
    }

    @Override
    public Optional<TipMovCaja> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<TipMovCaja> deleteById(Long id) {
        Optional<TipMovCaja> TMCOpt = repository.findById(id);
        TMCOpt.ifPresent(TMCItem -> {
            repository.delete(TMCItem);
        });
        return TMCOpt;
    }

    @Override
    public List<TipMovCaja> findAll() {
        return (List<TipMovCaja>) repository.findAll();
    }
}
