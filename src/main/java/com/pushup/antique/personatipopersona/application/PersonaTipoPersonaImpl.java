package com.pushup.antique.personatipopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.personatipopersona.domain.model.PersonaTipoPersona;
import com.pushup.antique.personatipopersona.infrastructure.PersonaTipoPersonaRepository;

@Service
public class PersonaTipoPersonaImpl implements PersonaTipoPersonaService {
    @Autowired
    private PersonaTipoPersonaRepository repository;

    @Transactional
    @Override
    public PersonaTipoPersona save(PersonaTipoPersona tipoPersona){
        return repository.save(tipoPersona);
    }

    @Override
    public Optional<PersonaTipoPersona> update(Long id, PersonaTipoPersona tipoPersona) {
        Optional<PersonaTipoPersona> PTPOpt = repository.findById(id);
        if (PTPOpt.isPresent()) {
            PersonaTipoPersona PTPItem = PTPOpt.orElseThrow();
            PTPItem.setTipoPersona(tipoPersona.getTipoPersona());
            return Optional.of(repository.save(PTPItem));
        }
        return PTPOpt;
    }

    @Override
    public Optional<PersonaTipoPersona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<PersonaTipoPersona> deleteById(Long id) {
        Optional<PersonaTipoPersona> PTPOpt = repository.findById(id);
        PTPOpt.ifPresent(PTPItem -> {
            repository.delete(PTPItem);
        });
        return PTPOpt;
    }

    @Override
    public List<PersonaTipoPersona> findAll() {
        return (List<PersonaTipoPersona>) repository.findAll();
    }

}
