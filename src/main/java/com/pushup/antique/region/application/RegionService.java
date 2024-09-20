package com.pushup.antique.region.application;

import java.util.List;
import java.util.Optional;

import com.pushup.antique.region.domain.model.Region;

public interface RegionService {
    Region save(Region region);
    Optional<Region> update(Long id, Region region);
    Optional<Region> findById(Long id);
    Optional<Region> deleteById(Long id);
    List<Region> findAll();
}
