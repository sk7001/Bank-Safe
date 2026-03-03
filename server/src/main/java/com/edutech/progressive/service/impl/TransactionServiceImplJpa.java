package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.exception.AccountNotFoundException;
import com.edutech.progressive.exception.OutOfBalanceException;
import com.edutech.progressive.exception.WithdrawalLimitException;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.repository.TransactionRepository;
import com.edutech.progressive.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImplJpa implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
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
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(new Date());
        }

        String raw = transaction.getTransactionType() == null ? "" : transaction.getTransactionType().trim();
        String type = raw.toUpperCase();
        if ("DEBIT".equals(type))  type = "WITHDRAWAL";
        if ("CREDIT".equals(type)) type = "DEPOSIT";

        // Resolve effective account id strictly: association first, then FK
        int effectiveId = 0;
        if (transaction.getAccounts() != null && transaction.getAccounts().getAccountId() > 0) {
            effectiveId = transaction.getAccounts().getAccountId();
            transaction.setAccountId(effectiveId); // sync FK for persistence
        } else if (transaction.getAccountId() > 0) {
            effectiveId = transaction.getAccountId();
        }

        if (effectiveId <= 0) {
            throw new AccountNotFoundException("No account found for id: " + effectiveId);
        }

        Accounts acc = accountRepository.findByAccountId(effectiveId);
        if (acc == null) {
            throw new AccountNotFoundException("No account found for id: " + effectiveId);
        }

        double amount = transaction.getAmount();

        if ("WITHDRAWAL".equals(type)) {
            if (amount > 30000.0) {
                throw new WithdrawalLimitException("Withdrawal amount exceeds single transaction limit of 30000");
            }
            if (amount > acc.getBalance()) {
                throw new OutOfBalanceException("Insufficient balance for the requested withdrawal");
            }
        }

        Transactions saved = transactionRepository.save(transaction);

        double newBalance = acc.getBalance();
        if ("DEPOSIT".equals(type)) {
            newBalance += amount;
        } else if ("WITHDRAWAL".equals(type)) {
            newBalance -= amount;
        }
        acc.setBalance(newBalance);
        accountRepository.save(acc);

        return saved.getTransactionId();
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
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