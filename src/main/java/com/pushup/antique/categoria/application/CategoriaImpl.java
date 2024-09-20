package com.pushup.antique.categoria.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.categoria.domain.model.Categoria;
import com.pushup.antique.categoria.infrastucture.CategoriaRepository;

@Service
public class CategoriaImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository repository;

    @Transactional
    @Override
    public Categoria save(Categoria categoria){
        return repository.save(categoria);
    }

    @Override
    public Optional<Categoria> update(Long id, Categoria categoria) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoriaItem = categoriaOpt.orElseThrow();
            categoriaItem.setNombre(categoria.getNombre());
            return Optional.of(repository.save(categoriaItem));
        }
        return categoriaOpt;
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Categoria> deleteById(Long id) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        categoriaOpt.ifPresent(categoriaItem -> {
            repository.delete(categoriaItem);
        });
        return categoriaOpt;
    }

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) repository.findAll();
    }
}
