package com.pushup.antique.transaccion.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.transaccion.domain.model.Transaccion;
import com.pushup.antique.transaccion.infrastructure.TransaccionRepository;

@Service
public class TransaccionImpl implements TransaccionService {
    @Autowired
    private TransaccionRepository repository;

    @Transactional
    @Override
    public Transaccion save(Transaccion transaccion){
        return repository.save(transaccion);
    }

    @Override
    public Optional<Transaccion> update(Long id, Transaccion transaccion) {
        Optional<Transaccion> transaccionOpt = repository.findById(id);
        if (transaccionOpt.isPresent()) {
            Transaccion transaccionItem = transaccionOpt.orElseThrow();
            transaccionItem.setDespacho(transaccion.getDespacho());
            transaccionItem.setDetalleTransaccion(transaccion.getDetalleTransaccion());
            transaccionItem.setEstacionPago(transaccion.getEstacionPago());
            transaccionItem.setPersona(transaccion.getPersona());
            transaccionItem.setTipoTransaccion(transaccion.getTipoTransaccion());
            transaccion.setTransaccionMedioPago(transaccion.getTransaccionMedioPago());
            return Optional.of(repository.save(transaccionItem));
        }
        return transaccionOpt;
    }

    @Override
    public Optional<Transaccion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Transaccion> deleteById(Long id) {
        Optional<Transaccion> transaccionOpt = repository.findById(id);
        transaccionOpt.ifPresent(transaccionItem -> {
            repository.delete(transaccionItem);
        });
        return transaccionOpt;
    }

    @Override
    public List<Transaccion> findAll() {
        return (List<Transaccion>) repository.findAll();
    }
}
