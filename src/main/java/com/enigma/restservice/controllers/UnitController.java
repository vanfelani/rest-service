package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.models.ItemModel;
import com.enigma.restservice.models.PageAbleList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.models.UnitModel;
import com.enigma.restservice.repositories.UnitRepository;
import com.enigma.restservice.services.UnitService;
import java.lang.reflect.Type;
import java.util.List;
import javax.validation.Valid;
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

@RequestMapping("/units")
@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping()
    public ResponseMessage<PageAbleList<UnitModel>> findAll(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String description
    ) {
        if (size > 100) {
            size = 100;
        }
        Unit entity = new Unit(name, description);
        Sort.Direction direction = Sort.Direction.fromOptionalString(sort.toUpperCase()).orElse(Sort.Direction.ASC);
        Page<Unit> pageUnits = unitService.findAll(entity, page, size, direction);
        List<Unit> units = pageUnits.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<UnitModel>>() {
        }.getType();
        List<UnitModel> unitModels = modelMapper.map(units, type);
        PageAbleList<UnitModel> data = new PageAbleList(unitModels,
                pageUnits.getNumber(), pageUnits.getSize(), pageUnits.getTotalElements());
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<UnitModel> findById(@PathVariable Integer id) {
        Unit entity = unitService.findById(id);
        if (entity != null) {
            ModelMapper modelMapper = new ModelMapper();
            UnitModel model = modelMapper.map(entity, UnitModel.class);
            return ResponseMessage.success(model);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping
    public ResponseMessage<UnitModel> add(@RequestBody @Valid UnitModel model) {
        Unit entity = unitService.save(new Unit(model.getName(), model.getDescription()));
        ModelMapper modelMapper = new ModelMapper();
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<UnitModel> editById(@PathVariable Integer id, @RequestBody @Valid UnitModel model) {
        ModelMapper modelMapper = new ModelMapper();

        model.setId(id);

        Unit entity = unitService.findById(id);
        modelMapper.map(model, entity);

        entity = unitService.save(entity);

        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<UnitModel> removeById(@PathVariable Integer id) {
        Unit entity = unitService.removeById(id);
        ModelMapper modelMapper = new ModelMapper();
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }

}
