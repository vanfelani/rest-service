package com.enigma.restservice.controllers;

import com.enigma.restservice.dto.StockSummary;
import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.models.PageAbleList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.models.StockModel;
import com.enigma.restservice.services.ItemService;
import com.enigma.restservice.services.StockService;
import com.enigma.restservice.services.UnitService;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UnitService unitService;

    @PostMapping
    public ResponseMessage<StockModel> add(@RequestBody StockModel model) {
        Item item = itemService.findById(model.getItem().getId());

        Unit unit = unitService.findById(model.getUnit().getId());

        Stock entity = stockService.save(new Stock(item, model.getQuantity(), unit));
        ModelMapper modelMapper = new ModelMapper();
        StockModel data = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockModel> editById(@PathVariable Integer id, @RequestBody StockModel model) {
        ModelMapper modelMapper = new ModelMapper();

        model.setId(id);

        Item item = itemService.findById(model.getItem().getId());

        Unit unit = unitService.findById(model.getUnit().getId());

        Stock entity = stockService.findById(id);
        modelMapper.map(model, entity);
        entity.setItem(item);
        entity.setUnit(unit);
        entity = stockService.save(entity);

        StockModel data = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<StockModel> removeById(@PathVariable Integer id) {
        Stock entity = stockService.removeById(id);
        ModelMapper modelMapper = new ModelMapper();
        StockModel model = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(model);
    }

    @GetMapping()
    public ResponseMessage<PageAbleList<StockModel>> findAll(@RequestParam(required = false) String name,
            @RequestParam(required = false) Item item,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Unit unit,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }
        Stock entity = new Stock(item, quantity, unit);
        Sort.Direction direction = Sort.Direction.fromOptionalString(sort.toUpperCase()).orElse(Sort.Direction.ASC);
        Page<Stock> pageStocks = stockService.findAll(entity, page, size, direction);
        List<Stock> stocks = pageStocks.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<StockModel>>() {
        }.getType();
        List<StockModel> stockModels = modelMapper.map(stocks, type);
        PageAbleList<StockModel> data = new PageAbleList(stockModels,
                pageStocks.getNumber(), pageStocks.getSize(), pageStocks.getTotalElements());
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<StockModel> findById(@PathVariable Integer id) {
        Stock entity = stockService.findById(id);
        if (entity != null) {
            ModelMapper modelMapper = new ModelMapper();
            StockModel model = modelMapper.map(entity, StockModel.class);
            return ResponseMessage.success(model);
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    @GetMapping(path = "/summary", produces = "application/json")
    public ResponseMessage<List<StockSummary>> summary(){
        List<StockSummary> entity = stockService.summary();
        return ResponseMessage.success(entity);
    }

}
