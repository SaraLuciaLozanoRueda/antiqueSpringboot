package com.pushup.antique.estadopersona.infrastrucure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.estadopersona.domain.model.EstadoPersona;

public interface EstadoPersonaRepository extends CrudRepository<EstadoPersona,Long> {

}
