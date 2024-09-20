package com.pushup.antique.movcaja.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.movcaja.domain.model.MovCaja;
import com.pushup.antique.movcaja.infrastructure.MovCajaRepository;

@Service
public class MovCajaImpl implements MovCajaService {
    @Autowired
    private MovCajaRepository repository;

    @Transactional
    @Override
    public MovCaja save(MovCaja movCaja){
        return repository.save(movCaja);
    }

    @Override
    public Optional<MovCaja> update(Long id, MovCaja movCaja) {
        Optional<MovCaja> movCajaOpt = repository.findById(id);
        if (movCajaOpt.isPresent()) {
            MovCaja movCajaItem = movCajaOpt.orElseThrow();
            movCajaItem.setEstacionPago(movCaja.getEstacionPago());
            movCajaItem.setTipMovCaja(movCaja.getTipMovCaja());
            return Optional.of(repository.save(movCajaItem));
        }
        return movCajaOpt;
    }

    @Override
    public Optional<MovCaja> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<MovCaja> deleteById(Long id) {
        Optional<MovCaja> movCajaOpt = repository.findById(id);
        movCajaOpt.ifPresent(movCajaItem -> {
            repository.delete(movCajaItem);
        });
        return movCajaOpt;
    }

    @Override
    public List<MovCaja> findAll() {
        return (List<MovCaja>) repository.findAll();
    }

}
