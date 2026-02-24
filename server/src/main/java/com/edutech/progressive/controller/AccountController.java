package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;

import java.sql.SQLException;
import java.util.List;

public class AccountController {

    @Autowired
    AccountService accountService;

    public ResponseEntity<List<Accounts>> getAllAccounts() throws SQLException {
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    public ResponseEntity<Accounts> getAccountById(int accountId) throws SQLException {
        return ResponseEntity.status(200).body(accountService.getAccountById(accountId));
    }

    public ResponseEntity<List<Accounts>> getAccountsByUser(String param) throws NumberFormatException, SQLException {
        return ResponseEntity.status(200).body(accountService.getAccountsByUser(Integer.parseInt(param)));
    }

    public ResponseEntity<Integer> addAccount(Accounts accounts) throws SQLException {
        return ResponseEntity.status(200).body(accountService.addAccount(accounts));
    }

    public ResponseEntity<Void> updateAccount(int accountId, Accounts accounts) throws SQLException {
        accountService.updateAccount(accounts);
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Void> deleteAccount(int accountId) throws SQLException {
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(200).build();
    }
}