package com.pushup.antique.coleccionista.domain.model;

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
@Table(name = "coleccionistas")
public class Coleccionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingresa el Id de la persona!")
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @NotNull(message = "Especifique la cantidad de colleciones!")
    private int numeroColecciones;

    public Coleccionista() {
    }

    public Coleccionista(Persona persona, int numeroColecciones) {
        this.persona = persona;
        this.numeroColecciones = numeroColecciones;
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

    public int getNumeroColecciones() {
        return numeroColecciones;
    }

    public void setNumeroColecciones(int numeroColecciones) {
        this.numeroColecciones = numeroColecciones;
    }

    
}
