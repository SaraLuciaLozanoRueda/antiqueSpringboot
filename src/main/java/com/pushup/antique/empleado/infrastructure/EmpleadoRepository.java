package com.pushup.antique.empleado.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.empleado.domain.model.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado,Long>{

}
