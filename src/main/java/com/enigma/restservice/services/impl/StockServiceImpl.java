package com.enigma.restservice.services.impl;

import com.enigma.restservice.dto.StockSummary;
import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.repositories.StockRepository;
import com.enigma.restservice.services.StockService;
import java.util.List;
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
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Stock save(Stock entity) {
        return stockRepository.save(entity);
    }

    @Override
    public Stock removeById(Integer id) {
        Stock entity = findById(id);
        stockRepository.delete(entity);
        return entity;
    }

    @Override
    public Stock findById(Integer id) {
        return stockRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Stock> findAll(Stock entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by(direction, "id") : Sort.by("id");

        ExampleMatcher matcher = ExampleMatcher.matchingAll().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return stockRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

    @Override
    public List<StockSummary> summary() {
     return stockRepository.summary();
    }

}
