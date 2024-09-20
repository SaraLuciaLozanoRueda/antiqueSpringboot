package com.pushup.antique.tipotransaccion.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.tipotransaccion.domain.models.TipoTransaccion;
import com.pushup.antique.tipotransaccion.infrastructure.TipoTransaccionRepository;

@Service
public class TipoTransaccionImpl implements TipoTransaccionService{
    @Autowired
    private TipoTransaccionRepository repository;

    @Transactional
    @Override
    public TipoTransaccion save(TipoTransaccion tipoTransaccion){
        return repository.save(tipoTransaccion);
    }

    @Override
    public Optional<TipoTransaccion> update(Long id, TipoTransaccion tipoTransaccion) {
        Optional<TipoTransaccion> TTransaccionOpt = repository.findById(id);
        if (TTransaccionOpt.isPresent()) {
            TipoTransaccion TTransaccion = TTransaccionOpt.orElseThrow();
            TTransaccion.setNombre(tipoTransaccion.getNombre());
            return Optional.of(repository.save(TTransaccion));
        }
        return TTransaccionOpt;
    }

    @Override
    public Optional<TipoTransaccion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<TipoTransaccion> deleteById(Long id) {
        Optional<TipoTransaccion> TTransaccionOpt = repository.findById(id);
        TTransaccionOpt.ifPresent(TTransaccion -> {
            repository.delete(TTransaccion);
        });
        return TTransaccionOpt;
    }

    @Override
    public List<TipoTransaccion> findAll() {
        return (List<TipoTransaccion>) repository.findAll();
    }
}
