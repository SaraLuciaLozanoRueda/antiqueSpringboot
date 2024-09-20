package com.pushup.antique.despacho.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.despacho.domain.model.Despacho;
import com.pushup.antique.despacho.infrastructure.DespachoRepository;

@Service
public class DespachoImpl implements DespachoService {
    @Autowired
    private DespachoRepository repository;

    @Transactional
    @Override
    public Despacho save(Despacho despacho){
        return repository.save(despacho);
    }

    @Override
    public Optional<Despacho> update(Long id, Despacho despacho) {
        Optional<Despacho> despachoOpt = repository.findById(id);
        if (despachoOpt.isPresent()) {
            Despacho despachoItem = despachoOpt.orElseThrow();
            despachoItem.setNombre_despacho(despacho.getNombre_despacho());
            return Optional.of(repository.save(despachoItem));
        }
        return despachoOpt;
    }

    @Override
    public Optional<Despacho> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Despacho> deleteById(Long id) {
        Optional<Despacho> despachoOpt = repository.findById(id);
        despachoOpt.ifPresent(despachoItem -> {
            repository.delete(despachoItem);
        });
        return despachoOpt;
    }

    @Override
    public List<Despacho> findAll() {
        return (List<Despacho>) repository.findAll();
    }
}
