package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;
@Service
public class CustomerServiceImplJpa implements CustomerService {

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return null;
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        return -1;
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        return null;    
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        return null;
    }
    
}