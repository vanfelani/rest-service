package com.enigma.restservice.repositories;

import com.enigma.restservice.dto.StockSummary;
import com.enigma.restservice.entities.Stock;
import java.util.List;

public interface StockRepositoryCustom {
    public List<StockSummary> summary();
}
