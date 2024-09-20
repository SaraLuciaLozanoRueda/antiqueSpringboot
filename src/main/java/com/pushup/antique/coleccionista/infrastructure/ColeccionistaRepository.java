package com.pushup.antique.coleccionista.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.coleccionista.domain.model.Coleccionista;

public interface ColeccionistaRepository  extends CrudRepository<Coleccionista,Long>{

}
