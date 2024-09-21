package com.pushup.antique.persona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.persona.domain.model.Persona;
import com.pushup.antique.persona.infrastructure.PersonaRepository;

@Service
public class PersonaImpl implements PersonaService{
    @Autowired
    private PersonaRepository repository;

    @Transactional
    @Override
    public Persona save(Persona persona){
        return repository.save(persona);
    }

    @Override
    public Optional<Persona> update(Long id, Persona persona) {
        Optional<Persona> personaOpt = repository.findById(id);
        if (personaOpt.isPresent()) {
            Persona personaItem = personaOpt.orElseThrow();
            personaItem.setEstadoPersona(persona.getEstadoPersona());
            personaItem.setdIreccionPersona(persona.getdIreccionPersona());
            personaItem.setGenero(persona.getGenero());
            personaItem.setPersonaTipoPersona(persona.getPersonaTipoPersona());
            personaItem.setContactoPersona(persona.getContactoPersona());
            personaItem.setEdad(persona.getEdad());
            personaItem.setNombre(persona.getNombre());
            return Optional.of(repository.save(personaItem));
        }
        return personaOpt;
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Persona> deleteById(Long id) {
        Optional<Persona> personaOpt = repository.findById(id);
        personaOpt.ifPresent(personaItem -> {
            repository.delete(personaItem);
        });
        return personaOpt;
    }

    @Override
    public List<Persona> findAll() {
        return (List<Persona>) repository.findAll();
    }
}
