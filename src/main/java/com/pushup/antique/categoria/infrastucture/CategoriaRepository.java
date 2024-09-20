package com.pushup.antique.categoria.infrastucture;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.categoria.domain.model.Categoria;

public interface CategoriaRepository  extends CrudRepository<Categoria,Long>{

}
