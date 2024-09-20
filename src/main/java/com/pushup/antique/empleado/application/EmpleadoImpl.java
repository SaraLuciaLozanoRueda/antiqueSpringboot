package com.pushup.antique.empleado.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.empleado.domain.model.Empleado;
import com.pushup.antique.empleado.infrastructure.EmpleadoRepository;

@Service
public class EmpleadoImpl implements EmpleadoService{
    @Autowired
    private EmpleadoRepository repository;

    @Transactional
    @Override
    public Empleado save(Empleado empleado){
        return repository.save(empleado);
    }

    @Override
    public Optional<Empleado> update(Long id, Empleado empleado) {
        Optional<Empleado> empleadoOpt = repository.findById(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleadoItem = empleadoOpt.orElseThrow();
            empleadoItem.setPersona(empleado.getPersona());
            empleadoItem.setCargo(empleado.getCargo());
            return Optional.of(repository.save(empleadoItem));
        }
        return empleadoOpt;
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Empleado> deleteById(Long id) {
        Optional<Empleado> empleadoOpt = repository.findById(id);
        empleadoOpt.ifPresent(empleadoItem -> {
            repository.delete(empleadoItem);
        });
        return empleadoOpt;
    }

    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) repository.findAll();
    }
}
