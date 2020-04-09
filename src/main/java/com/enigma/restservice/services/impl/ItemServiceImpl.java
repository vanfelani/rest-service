package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.repositories.ItemRepository;
import com.enigma.restservice.services.ItemService;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public Item save(Item entity) {

        return repository.save(entity);
    }

    @Override
    public Item removeById(Integer id) {
        Item entity = findById(id);
        repository.delete(entity);
        return entity;

    }

    @Override
    public Item findById(Integer id) {

        return repository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Item> findAll(Item entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by(direction, "id"): Sort.by("id");
        
        ExampleMatcher matcher = ExampleMatcher.matchingAll().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return repository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

//    @Override
//    public List<Item> findByNameLike(String name, boolean ignoreCase) {
//        if (!name.isEmpty()) {
//            return ignoreCase ? repository.findByNameContaining(name)
//                    : repository.findByNameLike(name);
//        } else {
//            return repository.findAll();
//        }
//
//    }


}
