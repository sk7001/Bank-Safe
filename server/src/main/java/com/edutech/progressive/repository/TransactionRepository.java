package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    // Find all transactions by account id (raw FK; no need for associations for Day 8)
    List<Transactions> findByAccountId(int accountId);

    // (Optional for later days)
    // void deleteByAccountId(int accountId);
}