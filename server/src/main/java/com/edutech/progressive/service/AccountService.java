package com.edutech.progressive.service;

import com.edutech.progressive.entity.Accounts;
import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    List<Accounts> getAllAccounts() throws SQLException;
    Accounts getAccountById(int accountId) throws SQLException;
    int addAccount(Accounts accounts) throws SQLException;
    void updateAccount(Accounts accounts) throws SQLException;
    void deleteAccount(int accountId) throws SQLException;
    List<Accounts> getAllAccountsSortedByBalance() throws SQLException;
    List<Accounts> getAccountsByUser(int userId) throws SQLException;
}