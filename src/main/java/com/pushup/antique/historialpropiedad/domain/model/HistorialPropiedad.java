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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "historial")
public class HistorialPropiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese el Id de la persona!")
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @NotNull(message = "Digite el Id de la antiguedad!")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Antiguedad getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Antiguedad antiguedad) {
        this.antiguedad = antiguedad;
    }

    
    
}
