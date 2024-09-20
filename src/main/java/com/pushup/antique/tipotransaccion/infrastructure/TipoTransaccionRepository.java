package com.pushup.antique.tipotransaccion.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.tipotransaccion.domain.models.TipoTransaccion;

public interface TipoTransaccionRepository extends CrudRepository<TipoTransaccion,Long> {

}
