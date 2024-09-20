package com.pushup.antique.personatipopersona.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.personatipopersona.domain.model.PersonaTipoPersona;


public interface PersonaTipoPersonaService {
    PersonaTipoPersona save(PersonaTipoPersona tipoPersona);
    Optional<PersonaTipoPersona> update(Long id, PersonaTipoPersona tipoPersona);
    Optional<PersonaTipoPersona> findById(Long id);
    Optional<PersonaTipoPersona> deleteById(Long id);
    List<PersonaTipoPersona> findAll();
}
