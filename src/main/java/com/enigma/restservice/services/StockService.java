package com.enigma.restservice.services;

import com.enigma.restservice.dto.StockSummary;
import com.enigma.restservice.entities.Stock;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface StockService {

    public Stock save(Stock entity);

    public Stock removeById(Integer id);

    public Stock findById(Integer id);

    public Page<Stock> findAll(Stock entity, int page, int size, Sort.Direction direction);
    
    public List<StockSummary> summary();
}
