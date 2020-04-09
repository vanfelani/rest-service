package com.enigma.restservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "stock")
@Entity
public class Stock extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Unit unit;

    public Stock(Item item, Integer quantity, Unit unit) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Stock() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("|%-8s| %-25s| %-12s| %-10s|", getId(), item.getName(), quantity, unit.getName());
    }

}
