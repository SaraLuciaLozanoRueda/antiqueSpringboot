package com.pushup.antique.personatipopersona.domain.model;

import com.pushup.antique.persona.domain.model.Persona;
import com.pushup.antique.tipopersona.domain.model.TipoPersona;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona_tipo_persona")
public class PersonaTipoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "tipoPersona_id")
    private TipoPersona tipoPersona;

    public PersonaTipoPersona() {
    }

    public PersonaTipoPersona(Persona persona, TipoPersona tipoPersona) {
        this.persona = persona;
        this.tipoPersona = tipoPersona;
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

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    
}
