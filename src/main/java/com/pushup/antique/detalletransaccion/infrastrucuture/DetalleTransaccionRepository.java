package com.pushup.antique.detalletransaccion.infrastrucuture;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.detalletransaccion.domain.model.DetalleTransaccion;

public interface DetalleTransaccionRepository extends CrudRepository<DetalleTransaccion,Long>{

}
