package com.pushup.antique.persona.domain.model;

import com.pushup.antique.contactopersona.domain.model.ContactoPersona;
import com.pushup.antique.direccionpersona.domain.model.DIreccionPersona;
import com.pushup.antique.estadopersona.domain.model.EstadoPersona;
import com.pushup.antique.genero.domain.model.Genero;
import com.pushup.antique.personatipopersona.domain.model.PersonaTipoPersona;

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
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese el nombre de la persona!")
    private String nombre;

    @NotNull(message = "Ingrese la edad!")
    private String edad;

    @NotNull(message = "Ingrese el id del estado de persona!")
    @ManyToOne
    @JoinColumn(name = "estadoPersona_id")
    private EstadoPersona estadoPersona;

    @NotNull(message = "Ingrese el id de la direccion de la persona!")
    @ManyToOne
    @JoinColumn(name = "dIreccionPersona_id")
    private DIreccionPersona dIreccionPersona;

    @NotNull(message = "Indique el Id del genero!")
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @NotNull(message = "Agregue el Id de la persona Tipo persona")
    @ManyToOne
    @JoinColumn(name = "personaTipoPersona_id")
    private PersonaTipoPersona personaTipoPersona;

    @NotNull(message = "No olvide ingresae el Id del contacto de la persona!")
    @ManyToOne
    @JoinColumn(name = "contactoPersona_id")
    private ContactoPersona contactoPersona;

    public Persona() {
    }

    public Persona(String nombre, String edad, EstadoPersona estadoPersona, DIreccionPersona dIreccionPersona,
            Genero genero, PersonaTipoPersona personaTipoPersona, ContactoPersona contactoPersona) {
        this.nombre = nombre;
        this.edad = edad;
        this.estadoPersona = estadoPersona;
        this.dIreccionPersona = dIreccionPersona;
        this.genero = genero;
        this.personaTipoPersona = personaTipoPersona;
        this.contactoPersona = contactoPersona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public EstadoPersona getEstadoPersona() {
        return estadoPersona;
    }

    public void setEstadoPersona(EstadoPersona estadoPersona) {
        this.estadoPersona = estadoPersona;
    }

    public DIreccionPersona getdIreccionPersona() {
        return dIreccionPersona;
    }

    public void setdIreccionPersona(DIreccionPersona dIreccionPersona) {
        this.dIreccionPersona = dIreccionPersona;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public PersonaTipoPersona getPersonaTipoPersona() {
        return personaTipoPersona;
    }

    public void setPersonaTipoPersona(PersonaTipoPersona personaTipoPersona) {
        this.personaTipoPersona = personaTipoPersona;
    }

    public ContactoPersona getContactoPersona() {
        return contactoPersona;
    }

    public void setContactoPersona(ContactoPersona contactoPersona) {
        this.contactoPersona = contactoPersona;
    }

    
}
