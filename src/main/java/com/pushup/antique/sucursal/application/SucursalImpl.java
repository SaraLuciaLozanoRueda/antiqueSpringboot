package com.pushup.antique.sucursal.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.sucursal.domain.model.Sucursal;
import com.pushup.antique.sucursal.infrastructure.SucursalRepository;

@Service
public class SucursalImpl implements SucursalService {
    @Autowired
    private SucursalRepository repository;

    @Transactional
    @Override
    public Sucursal save(Sucursal sucursal){
        return repository.save(sucursal);
    }

    @Override
    public Optional<Sucursal> update(Long id, Sucursal sucursal) {
        Optional<Sucursal> sucursalOpt = repository.findById(id);
        if (sucursalOpt.isPresent()) {
            Sucursal sucursaItem = sucursalOpt.orElseThrow();
            sucursaItem.setAntiguedad(sucursal.getAntiguedad());
            sucursaItem.setCiudad(sucursal.getCiudad());
            sucursaItem.setPersona(sucursal.getPersona());
            sucursaItem.setNombre(sucursal.getNombre());
            return Optional.of(repository.save(sucursaItem));
        }
        return sucursalOpt;
    }

    @Override
    public Optional<Sucursal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Sucursal> deleteById(Long id) {
        Optional<Sucursal> sucursalOpt = repository.findById(id);
        sucursalOpt.ifPresent(sucursaItem -> {
            repository.delete(sucursaItem);
        });
        return sucursalOpt;
    }

    @Override
    public List<Sucursal> findAll() {
        return (List<Sucursal>) repository.findAll();
    }
}
