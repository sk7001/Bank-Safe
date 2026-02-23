package com.edutech.progressive.dao;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Transactions;

public interface TransactionDAO {
    int addTransaction(Transactions transaction) throws SQLException;
    Transactions getTransactionById(int transactionId) throws SQLException;
    void updateTransaction(Transactions transaction) throws SQLException;
    void deleteTransaction(int transactionId) throws SQLException;
    List<Transactions> getAllTransactions() throws SQLException;
}
