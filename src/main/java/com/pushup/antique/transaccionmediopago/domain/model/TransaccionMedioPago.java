package com.pushup.antique.transaccionmediopago.domain.model;

import com.pushup.antique.mediopago.domain.model.MedioPago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "transaccion_medio_pago")
public class TransaccionMedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese el Id del medio de pago!")
    @ManyToOne
    @JoinColumn(name = "medioPago_id")
    private MedioPago medioPago;

    public TransaccionMedioPago() {
    }


    public TransaccionMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }


}
