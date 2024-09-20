package com.pushup.antique.region.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushup.antique.region.domain.model.Region;
import com.pushup.antique.region.infrastructure.RegionRepository;

@Service
public class RegionImpl implements RegionService {
    @Autowired
    private RegionRepository repository;

    @Transactional
    @Override
    public Region save(Region region){
        return repository.save(region);
    }

    @Override
    public Optional<Region> update(Long id, Region region) {
        Optional<Region> regionOpt = repository.findById(id);
        if (regionOpt.isPresent()) {
            Region regionItem = regionOpt.orElseThrow();
            regionItem.setPais(region.getPais());
            regionItem.setNombre(region.getNombre());
            return Optional.of(repository.save(regionItem));
        }
        return regionOpt;
    }

    @Override
    public Optional<Region> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Region> deleteById(Long id) {
        Optional<Region> regionOpt = repository.findById(id);
        regionOpt.ifPresent(regionItem -> {
            repository.delete(regionItem);
        });
        return regionOpt;
    }

    @Override
    public List<Region> findAll() {
        return (List<Region>) repository.findAll();
    }
}
