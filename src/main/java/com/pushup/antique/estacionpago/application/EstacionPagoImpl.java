package com.pushup.antique.estacionpago.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.estacionpago.domain.model.EstacionPago;
import com.pushup.antique.estacionpago.infrastructure.EstacionPagoRepositry;

@Service
public class EstacionPagoImpl implements EstacionPagoService{
    @Autowired
    private EstacionPagoRepositry repository;

    @Transactional
    @Override
    public EstacionPago save(EstacionPago estacionPago){
        return repository.save(estacionPago);
    }

    @Override
    public Optional<EstacionPago> update(Long id, EstacionPago estacionPago) {
        Optional<EstacionPago> estacionPagoOpt = repository.findById(id);
        if (estacionPagoOpt.isPresent()) {
            EstacionPago estacionPagoItem = estacionPagoOpt.orElseThrow();
            estacionPagoItem.setNombre(estacionPago.getNombre());
            return Optional.of(repository.save(estacionPagoItem));
        }
        return estacionPagoOpt;
    }

    @Override
    public Optional<EstacionPago> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<EstacionPago> deleteById(Long id) {
        Optional<EstacionPago> estacionPagoOpt = repository.findById(id);
        estacionPagoOpt.ifPresent(estacionPagoItem -> {
            repository.delete(estacionPagoItem);
        });
        return estacionPagoOpt;
    }

    @Override
    public List<EstacionPago> findAll() {
        return (List<EstacionPago>) repository.findAll();
    }
}
