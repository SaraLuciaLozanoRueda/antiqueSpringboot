package com.pushup.antique.antiguedades.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;

public interface AntiguedadRepository extends CrudRepository<Antiguedad, Long>{

}
