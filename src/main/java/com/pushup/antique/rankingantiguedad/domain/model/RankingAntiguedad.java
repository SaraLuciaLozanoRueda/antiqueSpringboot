package com.pushup.antique.rankingantiguedad.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ranking")
public class RankingAntiguedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese el numero de votaciones de la antiguedad!")
    private int votos;

    public RankingAntiguedad() {
    }

    public RankingAntiguedad(int votos) {
        this.votos = votos;
    }

    /* GETTERS AND SETTERS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    
}
