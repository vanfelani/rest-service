package com.enigma.restservice.models;

import com.enigma.restservice.dto.TransactionSummaryEntry;
import java.time.LocalDate;
import java.util.List;

public class TransactionSummary {
    private List<TransactionSummaryEntry> entries;
    private LocalDate from;
    private LocalDate to;
    
    public TransactionSummary() {
        
    }

    public TransactionSummary(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public TransactionSummary(List<TransactionSummaryEntry> entries) {
        this.entries = entries;
    }

    public List<TransactionSummaryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TransactionSummaryEntry> entries) {
        this.entries = entries;
    }
    

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
    
    
}
