package com.pushup.antique.transaccionmediopago.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.transaccionmediopago.domain.model.TransaccionMedioPago;

public interface TransaccionMedioPagoRepository  extends CrudRepository<TransaccionMedioPago,Long>{

}
