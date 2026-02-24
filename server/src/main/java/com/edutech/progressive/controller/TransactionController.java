package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.TransactionService;

import java.sql.SQLException;
import java.util.List;

public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public ResponseEntity<List<Transactions>> getAllTransactions() throws SQLException {
        return ResponseEntity.status(200).body(transactionService.getAllTransactions());
    }

    public ResponseEntity<Transactions> getTransactionById(int transactionId) throws SQLException {
        return ResponseEntity.status(200).body(transactionService.getTransactionById(transactionId));
    }

    public ResponseEntity<Integer> addTransaction(Transactions transaction) throws SQLException {
        return ResponseEntity.status(200).body(transactionService.addTransaction(transaction));
    }

    public ResponseEntity<Void> updateTransaction(int transactionId, Transactions transaction) throws SQLException {
        transactionService.updateTransaction(transaction);
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Void> deleteTransaction(int transactionId) throws SQLException {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.status(200).build();
    }
}
