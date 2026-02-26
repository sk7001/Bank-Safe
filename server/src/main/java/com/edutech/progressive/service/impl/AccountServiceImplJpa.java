package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.service.AccountService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountServiceImplJpa implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImplJpa(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountRepository.findAll();
    }

    @Override
    public List<Accounts> getAccountsByUser(int userId) throws SQLException {
        return accountRepository.findByCustomerId(userId);
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        return accountRepository.findByAccountId(accountId);
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
    public void deleteAccount(int accountId) throws SQLException {
        Accounts existing = accountRepository.findByAccountId(accountId);
        if (existing != null) {
            accountRepository.delete(existing);
        }
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> list = accountRepository.findAll();
        list.sort(Comparator.comparingDouble(Accounts::getBalance));
        return list;
    }
}