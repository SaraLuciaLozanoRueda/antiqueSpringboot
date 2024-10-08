package com.pushup.antique.direccionpersona.domain.model;

import com.pushup.antique.tipodireccion.domain.model.TipoDIreccion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "direccion_persona")
public class DIreccionPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese el Id del tipo de direccion!")
    @ManyToOne
    @JoinColumn(name = "tipoDIreccion_id")
    private TipoDIreccion tipoDIreccion;

    @NotBlank(message = "Ingrese la direccion!")
    private String direccion;

    public DIreccionPersona() {
    }

    public DIreccionPersona(TipoDIreccion tipoDIreccion, String direccion) {
        this.tipoDIreccion = tipoDIreccion;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDIreccion getTipoDIreccion() {
        return tipoDIreccion;
    }

    public void setTipoDIreccion(TipoDIreccion tipoDIreccion) {
        this.tipoDIreccion = tipoDIreccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
