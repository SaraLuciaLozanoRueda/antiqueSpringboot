package com.pushup.antique.persona.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.persona.domain.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona,Long>{

}
