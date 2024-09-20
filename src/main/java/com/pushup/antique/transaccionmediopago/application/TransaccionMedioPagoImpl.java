package com.pushup.antique.transaccionmediopago.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.transaccionmediopago.domain.model.TransaccionMedioPago;
import com.pushup.antique.transaccionmediopago.infrastructure.TransaccionMedioPagoRepository;

@Service
public class TransaccionMedioPagoImpl implements TransaccionMedioPagoService{
    @Autowired
    private TransaccionMedioPagoRepository repository;

    @Transactional
    @Override
    public TransaccionMedioPago save(TransaccionMedioPago TMP){
        return repository.save(TMP);
    }

    @Override
    public Optional<TransaccionMedioPago> update(Long id, TransaccionMedioPago TMP) {
        Optional<TransaccionMedioPago> TMPOpt = repository.findById(id);
        if (TMPOpt.isPresent()) {
            TransaccionMedioPago TMPItem = TMPOpt.orElseThrow();
            TMPItem.setMedioPago(TMP.getMedioPago());
            TMPItem.setTransaccion(TMP.getTransaccion());
            return Optional.of(repository.save(TMPItem));
        }
        return TMPOpt;
    }

    @Override
    public Optional<TransaccionMedioPago> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<TransaccionMedioPago> deleteById(Long id) {
        Optional<TransaccionMedioPago> TMPOpt = repository.findById(id);
        TMPOpt.ifPresent(TMPItem -> {
            repository.delete(TMPItem);
        });
        return TMPOpt;
    }

    @Override
    public List<TransaccionMedioPago> findAll() {
        return (List<TransaccionMedioPago>) repository.findAll();
    }
}
