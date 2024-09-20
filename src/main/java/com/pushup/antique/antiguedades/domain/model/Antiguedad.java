package com.pushup.antique.antiguedades.domain.model;

import com.pushup.antique.categoria.domain.model.Categoria;
import com.pushup.antique.ciudad.domain.model.Ciudad;
import com.pushup.antique.epocaantiguedad.domain.model.EpocaAntiguedad;
import com.pushup.antique.galeria.domain.model.Galeria;
import com.pushup.antique.rankingantiguedad.domain.model.RankingAntiguedad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "antiguedades")
public class Antiguedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name="epocaAntiguedad_id")
    private EpocaAntiguedad epocaAntiguedad;

    @ManyToOne
    @JoinColumn(name = "galeria_id")
    private Galeria galeria;

    @ManyToOne
    @JoinColumn(name = "rankingAntiguedad_id")
    private RankingAntiguedad rankingAntiguedad;

    public Antiguedad() {
    }

    public Antiguedad(String nombre, Categoria categoria, Ciudad ciudad, EpocaAntiguedad epocaAntiguedad,
            Galeria galeria, RankingAntiguedad rankingAntiguedad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.epocaAntiguedad = epocaAntiguedad;
        this.galeria = galeria;
        this.rankingAntiguedad = rankingAntiguedad;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public EpocaAntiguedad getEpocaAntiguedad() {
        return epocaAntiguedad;
    }

    public void setEpocaAntiguedad(EpocaAntiguedad epocaAntiguedad) {
        this.epocaAntiguedad = epocaAntiguedad;
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }

    public RankingAntiguedad getRankingAntiguedad() {
        return rankingAntiguedad;
    }

    public void setRankingAntiguedad(RankingAntiguedad rankingAntiguedad) {
        this.rankingAntiguedad = rankingAntiguedad;
    }

    
}
