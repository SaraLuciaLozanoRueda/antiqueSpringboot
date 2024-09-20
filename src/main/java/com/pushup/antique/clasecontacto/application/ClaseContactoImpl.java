package com.pushup.antique.clasecontacto.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.clasecontacto.domain.model.ClaseContacto;
import com.pushup.antique.clasecontacto.infrastructure.ClaseContactoRepository;

@Service
public class ClaseContactoImpl implements ClaseContactoService{
    @Autowired
    private ClaseContactoRepository repository;

    @Transactional
    @Override
    public ClaseContacto save(ClaseContacto claseContacto){
        return repository.save(claseContacto);
    }

    @Override
    public Optional<ClaseContacto> update(Long id, ClaseContacto claseContacto) {
        Optional<ClaseContacto> claseContactoOpt = repository.findById(id);
        if (claseContactoOpt.isPresent()) {
            ClaseContacto claseContactoItem = claseContactoOpt.orElseThrow();
            claseContactoItem.setNombre(claseContacto.getNombre());
            return Optional.of(repository.save(claseContactoItem));
        }
        return claseContactoOpt;
    }

    @Override
    public Optional<ClaseContacto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ClaseContacto> deleteById(Long id) {
        Optional<ClaseContacto> claseContactoOpt = repository.findById(id);
        claseContactoOpt.ifPresent(claseContactoItem -> {
            repository.delete(claseContactoItem);
        });
        return claseContactoOpt;
    }

    @Override
    public List<ClaseContacto> findAll() {
        return (List<ClaseContacto>) repository.findAll();
    }
}
