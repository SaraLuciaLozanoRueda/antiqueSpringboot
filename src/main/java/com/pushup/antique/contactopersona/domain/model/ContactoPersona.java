package com.pushup.antique.contactopersona.domain.model;

import com.pushup.antique.clasecontacto.domain.model.ClaseContacto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacto_persona")
public class ContactoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "claseContacto_id")
    private ClaseContacto claseContacto;

    private int numero;

    public ContactoPersona() {
    }

    public ContactoPersona(ClaseContacto claseContacto, int numero) {
        this.claseContacto = claseContacto;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaseContacto getClaseContacto() {
        return claseContacto;
    }

    public void setClaseContacto(ClaseContacto claseContacto) {
        this.claseContacto = claseContacto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
}
