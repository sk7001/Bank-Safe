package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.exception.AccountNotFoundException;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.repository.TransactionRepository;
import com.edutech.progressive.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountServiceImplJpa implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository; // may be null in tests

    @Autowired
    public AccountServiceImplJpa(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountServiceImplJpa(AccountRepository accountRepository,
            TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountRepository.findAll();
    }

    @Override
    public List<Accounts> getAccountsByUser(int customerId) throws SQLException{
        List<Accounts> list = accountRepository.findByCustomerCustomerId(customerId);
        if (list == null || list.isEmpty()) {
            throw new SQLException("Unable to find account with the customer Id: " + customerId);
        }
        return list;
    }

    @Override
    public Accounts getAccountById(int accountId) throws AccountNotFoundException, SQLException {
        Accounts acc = accountRepository.findById(accountId).orElse(null);
        if (acc == null) {
            throw new AccountNotFoundException("Account not found for id: " + accountId);
        }
        return acc;
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        Accounts saved = accountRepository.save(accounts);
        return saved.getAccountId();
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        accountRepository.save(accounts);
    }

    @Override
    public void deleteAccount(int accountId) throws AccountNotFoundException {
        if (accountId <= 0) {
            throw new AccountNotFoundException("Account not found for id: " + accountId);
        }
        Accounts existing = accountRepository.findByAccountId(accountId);
        if (existing == null) {
            throw new AccountNotFoundException("Account not found for id: " + accountId);
        }
        if (transactionRepository != null) {
            try {
                transactionRepository.deleteByAccountId(accountId);
            } catch (Exception ignored) {
            }
        }
        accountRepository.delete(existing);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> list = accountRepository.findAll();
        list.sort(Comparator.comparingDouble(Accounts::getBalance));
        return list;
    }
}
