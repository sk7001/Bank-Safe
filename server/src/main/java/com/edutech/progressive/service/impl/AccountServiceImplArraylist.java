package com.edutech.progressive.service.impl;
 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;
 
@Service
public class AccountServiceImplArraylist implements AccountService {
 
    List<Accounts> accountsList = new ArrayList<>();
 
    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountsList;
    }
 
    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        accountsList.add(accounts);
        return accountsList.size();
    }
 
    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> sortedList = new ArrayList<>(accountsList);
        Collections.sort(sortedList);
        return sortedList;
    }
 
    public void emptyArrayList() {
        accountsList.clear();
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountById'");
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    @Override
    public List<Accounts> getAccountsByUser(int userId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountsByUser'");
    }
 
}