package com.enigma.restservice.repositories.impl;

import com.enigma.restservice.dto.TransactionSummaryEntry;
import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.repositories.TransactionRepositoryCustom;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TransactionSummaryEntry> findTransactionOn(LocalDate from, LocalDate to) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransactionSummaryEntry> query = builder.createQuery(TransactionSummaryEntry.class
        );
        Root<Transaction> root = query.from(Transaction.class);
        query
                .multiselect(
                        root.get("type"),
                        builder.sum(root.get("amount"))
                       
                )
                .where(
                        builder.between(
                                builder.function(
                                        "DATE",
                                        Date.class,
                                        root.get("createdDate")
                                ),
                                Date.valueOf(from),
                                Date.valueOf(to)
                        )
                )
                .groupBy(root.get("type"));
        return entityManager.createQuery(query).getResultList();
    }

}
