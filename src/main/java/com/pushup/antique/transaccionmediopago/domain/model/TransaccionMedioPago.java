package com.pushup.antique.transaccionmediopago.domain.model;

import com.pushup.antique.mediopago.domain.model.MedioPago;
import com.pushup.antique.transaccion.domain.model.Transaccion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "transaccion_medio_pago")
public class TransaccionMedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medioPago_id")
    private MedioPago medioPago;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    public TransaccionMedioPago() {
    }

    public TransaccionMedioPago(MedioPago medioPago, Transaccion transaccion) {
        this.medioPago = medioPago;
        this.transaccion = transaccion;
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

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    
}
