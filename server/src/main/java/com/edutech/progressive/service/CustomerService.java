package com.edutech.progressive.service;

import com.edutech.progressive.entity.Customers;
import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<Customers> getAllCustomers() throws SQLException;
    int addCustomer(Customers customers) throws SQLException;
    List<Customers> getAllCustomersSortedByName() throws SQLException;
    void updateCustomer(Customers customers) throws SQLException;
    void deleteCustomer(int customerId) throws SQLException;
    Customers getCustomerById(int customerId) throws SQLException;
}