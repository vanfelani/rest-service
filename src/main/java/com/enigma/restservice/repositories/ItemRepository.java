package com.enigma.restservice.repositories;

import com.enigma.restservice.entities.Item;
import java.sql.SQLException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemRepositoryCustom {

    public List<Item> findByNameContaining(String name);
}
