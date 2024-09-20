package com.pushup.antique.estacionpago.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.estacionpago.domain.model.EstacionPago;

public interface EstacionPagoRepositry extends CrudRepository<EstacionPago,Long>{

}
