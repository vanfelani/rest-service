package com.enigma.restservice.services;

import com.enigma.restservice.entities.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UnitService {

    public Page<Unit> findAll(Unit entity, int page, int size, Sort.Direction direction);
    
    public Unit findById(Integer id);
    
    public Unit save(Unit entity);
    
    public Unit removeById(Integer id);
}
