package com.pushup.antique.rankingantiguedad.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.rankingantiguedad.domain.model.RankingAntiguedad;
import com.pushup.antique.rankingantiguedad.infrastructure.RankingAntiguedadRepository;

@Service
public class RankingAntiguedadImpl implements RankingAntiguedadService{
    @Autowired
    private RankingAntiguedadRepository repository;

    @Transactional
    @Override
    public RankingAntiguedad save(RankingAntiguedad ranking){
        return repository.save(ranking);
    }

    @Override
    public Optional<RankingAntiguedad> update(Long id, RankingAntiguedad ranking) {
        Optional<RankingAntiguedad> rankingOpt = repository.findById(id);
        if (rankingOpt.isPresent()) {
            RankingAntiguedad rankingItem = rankingOpt.orElseThrow();
            rankingItem.setVotos(ranking.getVotos());
            return Optional.of(repository.save(rankingItem));
        }
        return rankingOpt;
    }

    @Override
    public Optional<RankingAntiguedad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RankingAntiguedad> deleteById(Long id) {
        Optional<RankingAntiguedad> rankingOpt = repository.findById(id);
        rankingOpt.ifPresent(rankingItem -> {
            repository.delete(rankingItem);
        });
        return rankingOpt;
    }

    @Override
    public List<RankingAntiguedad> findAll() {
        return (List<RankingAntiguedad>) repository.findAll();
    }
}
