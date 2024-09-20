package com.pushup.antique.epocaantiguedad.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.epocaantiguedad.domain.model.EpocaAntiguedad;
import com.pushup.antique.epocaantiguedad.infrastructure.EpocaAntiguedadRepository;

@Service
public class EpocaAntiguedadImpl implements EpocaAntiguedadService{
    @Autowired
    private EpocaAntiguedadRepository repository;

    @Transactional
    @Override
    public EpocaAntiguedad save(EpocaAntiguedad epocaAntiguedad){
        return repository.save(epocaAntiguedad);
    }

    @Override
    public Optional<EpocaAntiguedad> update(Long id, EpocaAntiguedad epocaAntiguedad) {
        Optional<EpocaAntiguedad> epocaAntiguedadOpt = repository.findById(id);
        if (epocaAntiguedadOpt.isPresent()) {
            EpocaAntiguedad epocaAntiguedadItem = epocaAntiguedadOpt.orElseThrow();
           epocaAntiguedadItem.setNombre(epocaAntiguedad.getNombre());
            return Optional.of(repository.save(epocaAntiguedadItem));
        }
        return epocaAntiguedadOpt;
    }

    @Override
    public Optional<EpocaAntiguedad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<EpocaAntiguedad> deleteById(Long id) {
        Optional<EpocaAntiguedad> epocaAntiguedadOpt = repository.findById(id);
        epocaAntiguedadOpt.ifPresent(epocaAntiguedadItem -> {
            repository.delete(epocaAntiguedadItem);
        });
        return epocaAntiguedadOpt;
    }

    @Override
    public List<EpocaAntiguedad> findAll() {
        return (List<EpocaAntiguedad>) repository.findAll();
    }
}
