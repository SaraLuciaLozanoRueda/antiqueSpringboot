package com.pushup.antique.empresa.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.empresa.domain.model.Empresa;
import com.pushup.antique.empresa.infrastructure.EmpresaRepository;

@Service
public class EmpresaImpl implements EmpresaService{
    @Autowired
    private EmpresaRepository repository;

    @Transactional
    @Override
    public Empresa save(Empresa empresa){
        return repository.save(empresa);
    }

    @Override
    public Optional<Empresa> update(Long id, Empresa empresa) {
        Optional<Empresa> empresaOpt = repository.findById(id);
        if (empresaOpt.isPresent()) {
            Empresa empresaItem = empresaOpt.orElseThrow();
            empresaItem.setSucursal(empresa.getSucursal());
            empresaItem.setNombre(empresa.getNombre());
            empresaItem.setFecha_fundacion(empresa.getFecha_fundacion());
            return Optional.of(repository.save(empresaItem));
        }
        return empresaOpt;
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Empresa> deleteById(Long id) {
        Optional<Empresa> empresaOpt = repository.findById(id);
        empresaOpt.ifPresent(empresaItem -> {
            repository.delete(empresaItem);
        });
        return empresaOpt;
    }

    @Override
    public List<Empresa> findAll() {
        return (List<Empresa>) repository.findAll();
    }
}
