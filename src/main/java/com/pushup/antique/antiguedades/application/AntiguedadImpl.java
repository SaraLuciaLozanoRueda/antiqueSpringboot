package com.pushup.antique.antiguedades.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.antiguedades.domain.model.Antiguedad;
import com.pushup.antique.antiguedades.infrastructure.AntiguedadRepository;

@Service
public class AntiguedadImpl implements AntiguedadService{
    @Autowired
    private AntiguedadRepository repository;

    @Transactional
    @Override
    public Antiguedad save(Antiguedad antiguedad){
        return repository.save(antiguedad);
    }

    @Override
    public Optional<Antiguedad> update(Long id, Antiguedad antiguedad) {
        Optional<Antiguedad> antiguedadOpt = repository.findById(id);
        if (antiguedadOpt.isPresent()) {
            Antiguedad antiguedadItem = antiguedadOpt.orElseThrow();
            antiguedadItem.setNombre(antiguedad.getNombre());
            antiguedadItem.setCategoria(antiguedad.getCategoria());
            antiguedadItem.setCiudad(antiguedad.getCiudad());
            antiguedadItem.setEpocaAntiguedad(antiguedad.getEpocaAntiguedad());
            antiguedadItem.setGaleria(antiguedad.getGaleria());
            antiguedadItem.setRankingAntiguedad(antiguedad.getRankingAntiguedad());
            return Optional.of(repository.save(antiguedadItem));
        }
        return antiguedadOpt;
    }

    @Override
    public Optional<Antiguedad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Antiguedad> deleteById(Long id) {
        Optional<Antiguedad> antiguedadOpt = repository.findById(id);
        antiguedadOpt.ifPresent(antiguedadItem -> {
            repository.delete(antiguedadItem);
        });
        return antiguedadOpt;
    }

    @Override
    public List<Antiguedad> findAll() {
        return (List<Antiguedad>) repository.findAll();
    }


}
