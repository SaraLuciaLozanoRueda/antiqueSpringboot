package com.pushup.antique.transaccion.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.transaccion.domain.model.Transaccion;

public interface TransaccionRepository extends CrudRepository<Transaccion,Long> {

}
