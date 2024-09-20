package com.pushup.antique.rankingantiguedad.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.rankingantiguedad.domain.model.RankingAntiguedad;

public interface RankingAntiguedadService {
    RankingAntiguedad save(RankingAntiguedad ranking);
    Optional<RankingAntiguedad> update(Long id, RankingAntiguedad ranking);
    Optional<RankingAntiguedad> findById(Long id);
    Optional<RankingAntiguedad> deleteById(Long id);
    List<RankingAntiguedad> findAll();
}
