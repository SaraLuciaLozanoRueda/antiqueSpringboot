package com.pushup.antique.sucursal.domain.model;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;
import com.pushup.antique.ciudad.domain.model.Ciudad;
import com.pushup.antique.persona.domain.model.Persona;

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
@Table(name = "sucursales")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Agregue el nombre de la sucursal!")
    private String nombre;

    @NotNull(message = "Ingrese el Id de la ciudad!")
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @NotNull(message = "Indique el Id de la persona!")
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @NotNull(message = "No olvide el Id de la antiguedad!")
    @ManyToOne
    @JoinColumn(name = "antiguedad_id")
    private Antiguedad antiguedad;

    public Sucursal() {
    }

    public Sucursal(String nombre, Ciudad ciudad, Persona persona, Antiguedad antiguedad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.persona = persona;
        this.antiguedad = antiguedad;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
