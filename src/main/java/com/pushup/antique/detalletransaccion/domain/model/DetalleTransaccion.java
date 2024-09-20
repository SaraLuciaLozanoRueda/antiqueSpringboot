package com.pushup.antique.detalletransaccion.domain.model;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="detalle_transacciones" )
public class DetalleTransaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "antiguedad_id")
    private Antiguedad antiguedad;

    private String fecha_transaccion;

    public DetalleTransaccion() {
    }

    public DetalleTransaccion(Antiguedad antiguedad, String fecha_transaccion) {
        this.antiguedad = antiguedad;
        this.fecha_transaccion = fecha_transaccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Antiguedad getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Antiguedad antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getFecha_transaccion() {
        return fecha_transaccion;
    }

    public void setFecha_transaccion(String fecha_transaccion) {
        this.fecha_transaccion = fecha_transaccion;
    }

    
}   
