package com.enigma.restservice.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "transaction")
@Entity
public class Transaction extends AbstractEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @Convert(converter = TypeEnumConverter.class)
    private TypeEnum type;
    
    @Column(nullable = false)
    private String description;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, TypeEnum type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
