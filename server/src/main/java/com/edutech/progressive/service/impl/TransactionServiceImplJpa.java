package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.repository.TransactionRepository;
import com.edutech.progressive.service.TransactionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImplJpa implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImplJpa(TransactionRepository transactionRepository,
                                     AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        // Ensure timestamp is set
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(new Date());
        }

        // Persist the transaction first (so id is generated)
        Transactions saved = transactionRepository.save(transaction);

        // Update account balance (basic rules for Day 8; Day 9 adds validations/exceptions)
        Accounts acc = accountRepository.findByAccountId(transaction.getAccountId());
        if (acc != null) {
            String type = transaction.getTransactionType() != null
                    ? transaction.getTransactionType().trim().toUpperCase()
                    : "";

            double newBalance = acc.getBalance();
            if ("DEPOSIT".equals(type)) {
                newBalance += transaction.getAmount();
            } else if ("WITHDRAWAL".equals(type)) {
                newBalance -= transaction.getAmount();
            }
            acc.setBalance(newBalance);
            accountRepository.save(acc);
        }

        return saved.getTransactionId();
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        // NOTE: Day 8 does not require re-adjusting account balance on update.
        // Persist the new values as-is.
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(new Date());
        }
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        List<Transactions> result = new ArrayList<>();
        List<Accounts> accounts = accountRepository.findByCustomerId(customerId);
        for (Accounts a : accounts) {
            result.addAll(transactionRepository.findByAccountId(a.getAccountId()));
        }
        return result;
    }
}