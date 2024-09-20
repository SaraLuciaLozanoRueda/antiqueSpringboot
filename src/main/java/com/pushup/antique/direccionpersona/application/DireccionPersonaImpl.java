package com.pushup.antique.direccionpersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.direccionpersona.domain.model.DIreccionPersona;
import com.pushup.antique.direccionpersona.infrastructure.DireccionPersonaRepository;

@Service
public class DireccionPersonaImpl implements DireccionPersonaService{
    @Autowired
    private DireccionPersonaRepository repository;

    @Transactional
    @Override
    public DIreccionPersona save(DIreccionPersona direccionPersona){
        return repository.save(direccionPersona);
    }

    @Override
    public Optional<DIreccionPersona> update(Long id, DIreccionPersona direccionPersona) {
        Optional<DIreccionPersona> direccionPersonaOpt = repository.findById(id);
        if (direccionPersonaOpt.isPresent()) {
            DIreccionPersona direccionPersonaItem = direccionPersonaOpt.orElseThrow();
            direccionPersonaItem.setTipoDIreccion(direccionPersona.getTipoDIreccion());
            direccionPersonaItem.setDireccion(direccionPersona.getDireccion());
            return Optional.of(repository.save(direccionPersonaItem));
        }
        return direccionPersonaOpt;
    }

    @Override
    public Optional<DIreccionPersona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DIreccionPersona> deleteById(Long id) {
        Optional<DIreccionPersona> direccionPersonaOpt = repository.findById(id);
        direccionPersonaOpt.ifPresent(direccionPersonaItem -> {
            repository.delete(direccionPersonaItem);
        });
        return direccionPersonaOpt;
    }

    @Override
    public List<DIreccionPersona> findAll() {
        return (List<DIreccionPersona>) repository.findAll();
    }
}
