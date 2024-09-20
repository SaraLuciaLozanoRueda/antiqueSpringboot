package com.pushup.antique.empresa.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.empresa.domain.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa,Long>{

}
