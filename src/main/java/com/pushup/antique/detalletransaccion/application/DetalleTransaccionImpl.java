package com.pushup.antique.detalletransaccion.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.detalletransaccion.domain.model.DetalleTransaccion;
import com.pushup.antique.detalletransaccion.infrastrucuture.DetalleTransaccionRepository;

@Service
public class DetalleTransaccionImpl implements DetalleTransaccionService{
    @Autowired
    private DetalleTransaccionRepository repository;

    @Transactional
    @Override
    public DetalleTransaccion save(DetalleTransaccion detalleTransaccion){
        return repository.save(detalleTransaccion);
    }

    @Override
    public Optional<DetalleTransaccion> update(Long id, DetalleTransaccion detalleTransaccion) {
        Optional<DetalleTransaccion> detalleTransaccionOpt = repository.findById(id);
        if (detalleTransaccionOpt.isPresent()) {
            DetalleTransaccion detalleTransaccionItem = detalleTransaccionOpt.orElseThrow();
            detalleTransaccionItem.setAntiguedad(detalleTransaccion.getAntiguedad());
            detalleTransaccionItem.setFecha_transaccion(detalleTransaccion.getFecha_transaccion());
            return Optional.of(repository.save(detalleTransaccionItem));
        }
        return detalleTransaccionOpt;
    }

    @Override
    public Optional<DetalleTransaccion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DetalleTransaccion> deleteById(Long id) {
        Optional<DetalleTransaccion> detalleTransaccionOpt = repository.findById(id);
        detalleTransaccionOpt.ifPresent(detalleTransaccionItem -> {
            repository.delete(detalleTransaccionItem);
        });
        return detalleTransaccionOpt;
    }

    @Override
    public List<DetalleTransaccion> findAll() {
        return (List<DetalleTransaccion>) repository.findAll();
    }
}
