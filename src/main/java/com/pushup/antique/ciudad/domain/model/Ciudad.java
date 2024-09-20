package com.pushup.antique.ciudad.domain.model;


import com.pushup.antique.region.domain.model.Region;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    
    public Ciudad() {
    }


    public Ciudad(String nombre, Region region) {
        this.nombre = nombre;
        this.region = region;
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


    public Region getRegion() {
        return region;
    }


    public void setRegion(Region region) {
        this.region = region;
    }
    
    
}
