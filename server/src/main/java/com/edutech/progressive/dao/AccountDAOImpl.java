package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;

public class AccountDAOImpl implements AccountDAO {
    private Connection connection;

    public AccountDAOImpl() throws SQLException {
        this.connection = DatabaseConnectionManager.getConnection();
    }

    public List<Accounts> accountsList = new ArrayList<Accounts>();

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Accounts accounts = new Accounts(rs.getInt("account_id"), rs.getInt("customer_id"),
                    rs.getDouble("balance"));
            accountsList.add(accounts);

        }

        return accountsList;

    }

    public List<Accounts> getAllAccountsByCustomer(int customer_id) throws SQLException {
        List<Accounts> customerAccounts = new ArrayList<Accounts>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE customer_id=?;");
        ps.setInt(1, customer_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Accounts accounts = new Accounts(rs.getInt("account_id"), rs.getInt("customer_id"),
                    rs.getDouble("balance"));
            customerAccounts.add(accounts);
        }
        return customerAccounts;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE account_id=?;");
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Accounts accounts = new Accounts(rs.getInt("account_id"), rs.getInt("customer_id"),
                    rs.getDouble("balance"));
            return accounts;
        }
        return null;

    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException{

        PreparedStatement ps=connection.prepareStatement("INSERT INTO accounts (customer_id,balance) VALUES (?,?);",Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        int i=ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        if(i>0){
            if(rs.next()){
            accounts.setAccountId(rs.getInt(1));
            return rs.getInt(1);
        }
        

        } 
        return -1;
    }

   

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        PreparedStatement ps=connection.prepareStatement("UPDATE accounts SET customer_id=?,balance=? WHERE account_id=?;");
        ps.setInt(1, accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        ps.setInt(3,accounts.getAccountId());
        ps.executeUpdate();

        
    }


    

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        
    }

}
