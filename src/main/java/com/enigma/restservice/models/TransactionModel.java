package com.enigma.restservice.models;

import com.enigma.restservice.entities.TypeEnum;
import com.enigma.restservice.validation.annotations.MinLength;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;

public class TransactionModel {
    private Integer id;
    
    private BigDecimal amount;

    private TypeEnum type;
    
    @MinLength(3)
    @NotBlank(message = "{description.not.blank}")
    private String description;

    public TransactionModel() {
    }

    public TransactionModel(Integer id, BigDecimal amount, TypeEnum type, String description) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
