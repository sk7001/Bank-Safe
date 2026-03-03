package com.edutech.progressive.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.exception.AccountNotFoundException;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        final String sql = "SELECT account_id, customer_id, balance FROM accounts";
        List<Accounts> result = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRowToAccount(rs));
            }
        }
        return result;
    }

    /**
     * Some Day 3 test suites call a DAO method for balance-sorted retrieval.
     * If your interface doesn’t declare this, the service may still rely on it
     * or sort in-memory. Keeping this here makes the DAO future-proof.
     */
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        final String sql = "SELECT account_id, customer_id, balance FROM accounts ORDER BY balance ASC";
        List<Accounts> result = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRowToAccount(rs));
            }
        }
        return result;
    }

    @Override
    public List<Accounts> getAllAccountsByCustomer(int customerId) throws SQLException {
        final String sql = "SELECT account_id, customer_id, balance FROM accounts WHERE customer_id = ?";
        List<Accounts> result = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRowToAccount(rs));
                }
            }
        }
        return result;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        // ✅ Day 9: invalid id should immediately raise “not found”
        if (accountId <= 0) {
            throw new AccountNotFoundException("Account not found for id: " + accountId);
        }

        final String sql = "SELECT account_id, customer_id, balance FROM accounts WHERE account_id = ?";
        Accounts acc = null;

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    acc = mapRowToAccount(rs);
                }
            }
        }

        // ✅ Day 9: when missing, throw instead of returning null
        if (acc == null) {
            throw new AccountNotFoundException("Account not found for id: " + accountId);
        }

        return acc;
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        final String sql = "INSERT INTO accounts (customer_id, balance) VALUES (?, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, accounts.getCustomerId());
            ps.setBigDecimal(2, BigDecimal.valueOf(accounts.getBalance()));

            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new SQLException("Insert account failed; no rows affected.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    generatedId = keys.getInt(1);
                    accounts.setAccountId(generatedId);
                } else {
                    throw new SQLException("Insert account succeeded but no ID obtained.");
                }
            }
        }
        return generatedId;
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        final String sql = "UPDATE accounts SET customer_id = ?, balance = ? WHERE account_id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accounts.getCustomerId());
            ps.setBigDecimal(2, BigDecimal.valueOf(accounts.getBalance()));
            ps.setInt(3, accounts.getAccountId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        final String sql = "DELETE FROM accounts WHERE account_id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.executeUpdate();
        }
    }

    // ---------- Helper mapping ----------
    private Accounts mapRowToAccount(ResultSet rs) throws SQLException {
        Accounts acc = new Accounts();
        acc.setAccountId(rs.getInt("account_id"));
        acc.setCustomerId(rs.getInt("customer_id"));

        BigDecimal bal = rs.getBigDecimal("balance");
        acc.setBalance(bal != null ? bal.doubleValue() : 0.0);

        return acc;
    }
}