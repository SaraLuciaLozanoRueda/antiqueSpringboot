package com.pushup.antique.mediopago.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.mediopago.domain.model.MedioPago;
import com.pushup.antique.mediopago.infrastructure.MedioPagoRepository;

@Service
public class MedioPagoImpl implements MedioPagoService{
    @Autowired
    private MedioPagoRepository repository;

    @Transactional
    @Override
    public MedioPago save(MedioPago medioPago){
        return repository.save(medioPago);
    }

    @Override
    public Optional<MedioPago> update(Long id, MedioPago medioPago) {
        Optional<MedioPago> medioPagoOpt = repository.findById(id);
        if (medioPagoOpt.isPresent()) {
            MedioPago medioPagoItem = medioPagoOpt.orElseThrow();
            medioPagoItem.setNombre(medioPago.getNombre());
            return Optional.of(repository.save(medioPagoItem));
        }
        return medioPagoOpt;
    }

    @Override
    public Optional<MedioPago> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<MedioPago> deleteById(Long id) {
        Optional<MedioPago> medioPagoOpt = repository.findById(id);
        medioPagoOpt.ifPresent(medioPagoItem -> {
            repository.delete(medioPagoItem);
        });
        return medioPagoOpt;
    }

    @Override
    public List<MedioPago> findAll() {
        return (List<MedioPago>) repository.findAll();
    }
}
