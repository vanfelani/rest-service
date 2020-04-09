package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.repositories.UnitRepository;
import com.enigma.restservice.services.UnitService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public Page<Unit> findAll(Unit entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by(direction, "id") : Sort.by("id");

        ExampleMatcher matcher = ExampleMatcher.matchingAll().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return unitRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

    @Override
    public Unit findById(Integer id) {
        return unitRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });

    }

    @Override
    public Unit save(Unit entity) {
        return unitRepository.save(entity);
    }

    @Override
    public Unit removeById(Integer id) {
        Unit entity = findById(id);
        unitRepository.delete(entity);
        return entity;
    }


    
    

}
