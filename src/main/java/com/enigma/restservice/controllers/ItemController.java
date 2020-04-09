package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.models.ItemModel;
import com.enigma.restservice.models.PageAbleList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@RequestMapping("/items")
@RestController
@Api(value = "ITEM", description = "Controller Item", tags= {"items"})
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseMessage<ItemModel> add(@RequestBody @Valid ItemModel model) {
        Item entity = itemService.save(new Item(model.getName()));
        ModelMapper modelMapper = new ModelMapper();
        ItemModel data = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ItemModel> edit(@PathVariable Integer id, @RequestBody @Valid ItemModel model) {
        ModelMapper modelMapper = new ModelMapper();

        model.setId(id);
        Item entity = itemService.findById(id);
        modelMapper.map(model, entity);

        entity = itemService.save(entity);

        ItemModel data = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ItemModel> removeById(@PathVariable Integer id) {
        Item entity = itemService.removeById(id);
        ModelMapper modelMapper = new ModelMapper();
        ItemModel model = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(model);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ItemModel> findById(@PathVariable Integer id) {
        Item entity = itemService.findById(id);
        if (entity != null) {
            ModelMapper modelMapper = new ModelMapper();
            ItemModel model = modelMapper.map(entity, ItemModel.class);
            return ResponseMessage.success(model);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @ApiOperation(value = "Find All Item", tags = {"items"})
    @GetMapping()
    public ResponseMessage<PageAbleList<ItemModel>> findAll(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size>100) {
            size = 100;
        }
        Item entity = new Item(name);
        Sort.Direction direction = Sort.Direction.fromOptionalString(sort.toUpperCase()).orElse(Sort.Direction.ASC);
        Page<Item> pageItems = itemService.findAll(entity, page, size, direction);
        List<Item> items = pageItems.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<ItemModel>>() {
        }.getType();
        List<ItemModel> itemModels = modelMapper.map(items, type);
        PageAbleList<ItemModel> data = new PageAbleList(itemModels,
                pageItems.getNumber(), pageItems.getSize(),pageItems.getTotalElements());
        return ResponseMessage.success(data);
    }

}
