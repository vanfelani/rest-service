package com.enigma.restservice.dto;

import com.enigma.restservice.entities.TypeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class TransactionSummaryEntry {

    private BigDecimal amount;
    private TypeEnum type;
    
    public TransactionSummaryEntry() {
    }

    public TransactionSummaryEntry(TypeEnum type, BigDecimal amount) {
        this.amount = amount;
        this.type = type;
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

}
