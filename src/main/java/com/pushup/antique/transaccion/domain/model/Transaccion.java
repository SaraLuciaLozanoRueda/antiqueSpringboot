package com.pushup.antique.transaccion.domain.model;

import com.pushup.antique.despacho.domain.model.Despacho;
import com.pushup.antique.detalletransaccion.domain.model.DetalleTransaccion;
import com.pushup.antique.estacionpago.domain.model.EstacionPago;
import com.pushup.antique.persona.domain.model.Persona;
import com.pushup.antique.tipotransaccion.domain.models.TipoTransaccion;
import com.pushup.antique.transaccionmediopago.domain.model.TransaccionMedioPago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese el id de la persona!")
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @NotNull(message = "Indique el Id del tipo de transaccion!")
    @ManyToOne
    @JoinColumn(name = "tipoTransaccion_id")
    private TipoTransaccion tipoTransaccion;

    @NotNull(message = "Ingrese el Id del despacho!")
    @ManyToOne
    @JoinColumn(name = "despacho_id")
    private Despacho despacho;

    @NotNull(message = "Agregue el Id de la transacciion medio de pago!")
    @ManyToOne
    @JoinColumn(name = "transaccionMedioPago_id")
    private TransaccionMedioPago transaccionMedioPago;

    @NotNull(message = "No olvide el Id del detalle de la transaccion!")
    @ManyToOne
    @JoinColumn(name = "detalleTransaccion_id")
    private DetalleTransaccion detalleTransaccion;

    @NotNull(message = "Recuerde agregar el Id de la estaciond de pago!")
    @ManyToOne
    @JoinColumn(name = "estacionPago_id")
    private EstacionPago estacionPago;

    public Transaccion() {
    }

    public Transaccion(Persona persona, TipoTransaccion tipoTransaccion, Despacho despacho,
            TransaccionMedioPago transaccionMedioPago, DetalleTransaccion detalleTransaccion,
            EstacionPago estacionPago) {
        this.persona = persona;
        this.tipoTransaccion = tipoTransaccion;
        this.despacho = despacho;
        this.transaccionMedioPago = transaccionMedioPago;
        this.detalleTransaccion = detalleTransaccion;
        this.estacionPago = estacionPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Despacho getDespacho() {
        return despacho;
    }

    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }

    public TransaccionMedioPago getTransaccionMedioPago() {
        return transaccionMedioPago;
    }

    public void setTransaccionMedioPago(TransaccionMedioPago transaccionMedioPago) {
        this.transaccionMedioPago = transaccionMedioPago;
    }

    public DetalleTransaccion getDetalleTransaccion() {
        return detalleTransaccion;
    }

    public void setDetalleTransaccion(DetalleTransaccion detalleTransaccion) {
        this.detalleTransaccion = detalleTransaccion;
    }

    public EstacionPago getEstacionPago() {
        return estacionPago;
    }

    public void setEstacionPago(EstacionPago estacionPago) {
        this.estacionPago = estacionPago;
    }
    
    
}
