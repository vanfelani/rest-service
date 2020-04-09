package com.enigma.restservice.models;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Unit;

public class StockModel {

    private Integer id;

    private Integer quantity;

    private Item item;

    private Unit unit;

    public StockModel() {
    }

    public StockModel(Integer id, Integer quantity, Item item, Unit unit) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


}
