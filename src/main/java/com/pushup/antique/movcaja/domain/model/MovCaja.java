package com.pushup.antique.movcaja.domain.model;

import com.pushup.antique.estacionpago.domain.model.EstacionPago;
import com.pushup.antique.tipomovcaja.domain.model.TipMovCaja;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "mov_cajas")
public class MovCaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estacionPago_id")
    private  EstacionPago estacionPago;

    @ManyToOne
    @JoinColumn(name = "tipMovCaja_id")
    private TipMovCaja tipMovCaja;

    public MovCaja() {
    }

    public MovCaja(EstacionPago estacionPago, TipMovCaja tipMovCaja) {
        this.estacionPago = estacionPago;
        this.tipMovCaja = tipMovCaja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstacionPago getEstacionPago() {
        return estacionPago;
    }

    public void setEstacionPago(EstacionPago estacionPago) {
        this.estacionPago = estacionPago;
    }

    public TipMovCaja getTipMovCaja() {
        return tipMovCaja;
    }

    public void setTipMovCaja(TipMovCaja tipMovCaja) {
        this.tipMovCaja = tipMovCaja;
    }

    
}
