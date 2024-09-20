package com.pushup.antique.ciudad.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.ciudad.domain.model.Ciudad;

public interface CiudadRepository  extends CrudRepository<Ciudad,Long>{

}
