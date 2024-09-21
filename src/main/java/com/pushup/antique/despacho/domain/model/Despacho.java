package com.pushup.antique.despacho.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "despachos")
public class Despacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pongale un nombre al despacho!")
    private String nombre_despacho;

    public Despacho() {
    }

    public Despacho(String nombre_despacho) {
        this.nombre_despacho = nombre_despacho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_despacho() {
        return nombre_despacho;
    }

    public void setNombre_despacho(String nombre_despacho) {
        this.nombre_despacho = nombre_despacho;
    }

    
}
