package com.pushup.antique.tipopersona.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.tipopersona.domain.model.TipoPersona;

public interface TipoPersonaRepository extends  CrudRepository<TipoPersona,Long> {

}
