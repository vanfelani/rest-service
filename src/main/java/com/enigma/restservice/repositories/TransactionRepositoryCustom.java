package com.enigma.restservice.repositories;

import com.enigma.restservice.dto.TransactionSummaryEntry;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepositoryCustom {
    public List<TransactionSummaryEntry> findTransactionOn(LocalDate from, LocalDate to);
}
