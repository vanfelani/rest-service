package com.enigma.restservice.services.impl;

import com.enigma.restservice.dto.TransactionSummaryEntry;
import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.exception.EntityNotFoundException;
import com.enigma.restservice.models.TransactionSummary;
import com.enigma.restservice.repositories.TransactionRepository;
import com.enigma.restservice.services.TransactionService;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
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
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction entity) {
        return transactionRepository.save(entity);
    }

    @Override
    public Transaction removeById(Integer id) {
        Transaction entity = findById(id);
        transactionRepository.delete(entity);
        return entity;
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Transaction> findAll(Transaction entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by(direction, "id") : Sort.by("id");

        ExampleMatcher matcher = ExampleMatcher.matchingAll().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return transactionRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

    @Override
    public TransactionSummary summary(Year year, Month month, Integer date) {
        LocalDate from = LocalDate.of(year.getValue(), 1, 1);
        LocalDate to = LocalDate.of(year.getValue(), 12, 1);

        if (month != null) {
            from = from.withMonth(month.getValue());
            to = to.withMonth(month.getValue());
        }

        if (date != null) {
            from = from.withDayOfMonth(date);
            to = to.withDayOfMonth(date);
        } else {
            from = from.withDayOfMonth(1);
            to = to.with(TemporalAdjusters.lastDayOfMonth());
        }

        List<TransactionSummaryEntry> entries = transactionRepository.findTransactionOn(from, to);
        TransactionSummary summary = new TransactionSummary(entries);
        summary.setFrom(from);
        summary.setTo(to);
        return summary;
    }


}
