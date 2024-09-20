package com.pushup.antique.historialpropiedad.domain.model;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;
import com.pushup.antique.persona.domain.model.Persona;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial")
public class HistorialPropiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "antiguedad_id")
    private Antiguedad antiguedad;

    public HistorialPropiedad() {
    }

    public HistorialPropiedad(Persona persona, Antiguedad antiguedad) {
        this.persona = persona;
        this.antiguedad = antiguedad;
    }

    public Long getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public Antiguedad getAntiguedad() {
        return antiguedad;
    }

    
}
