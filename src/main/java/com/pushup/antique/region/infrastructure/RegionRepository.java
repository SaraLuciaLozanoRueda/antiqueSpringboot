package com.pushup.antique.region.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.pushup.antique.region.domain.model.Region;

public interface RegionRepository extends CrudRepository<Region,Long> {

}
