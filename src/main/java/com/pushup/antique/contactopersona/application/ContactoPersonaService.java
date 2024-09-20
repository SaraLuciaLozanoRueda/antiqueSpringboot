package com.pushup.antique.contactopersona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.contactopersona.domain.model.ContactoPersona;

public interface ContactoPersonaService {
    ContactoPersona save(ContactoPersona contactoPersona);
    Optional<ContactoPersona> update(Long id, ContactoPersona contactoPersona);
    Optional<ContactoPersona> findById(Long id);
    Optional<ContactoPersona> deleteById(Long id);
    List<ContactoPersona> findAll();
}
