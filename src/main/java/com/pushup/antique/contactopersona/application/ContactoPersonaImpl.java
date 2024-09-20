package com.pushup.antique.contactopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.contactopersona.domain.model.ContactoPersona;
import com.pushup.antique.contactopersona.infrastructure.ContactoPersonaRepository;

@Service
public class ContactoPersonaImpl implements ContactoPersonaService {
    @Autowired
    private ContactoPersonaRepository repository;

    @Transactional
    @Override
    public ContactoPersona save(ContactoPersona contactoPersona){
        return repository.save(contactoPersona);
    }

    @Override
    public Optional<ContactoPersona> update(Long id, ContactoPersona contactoPersona) {
        Optional<ContactoPersona> contactoPersonaOpt = repository.findById(id);
        if (contactoPersonaOpt.isPresent()) {
            ContactoPersona contactoPersonaItem = contactoPersonaOpt.orElseThrow();
            
            return Optional.of(repository.save(contactoPersonaItem));
        }
        return contactoPersonaOpt;
    }

    @Override
    public Optional<ContactoPersona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ContactoPersona> deleteById(Long id) {
        Optional<ContactoPersona> contactoPersonaOpt = repository.findById(id);
        contactoPersonaOpt.ifPresent(contactoPersonaItem -> {
            repository.delete(contactoPersonaItem);
        });
        return contactoPersonaOpt;
    }

    @Override
    public List<ContactoPersona> findAll() {
        return (List<ContactoPersona>) repository.findAll();
    }
}
