package com.edutech.progressive.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    @Autowired
    CustomerService customerService;

    public ResponseEntity<List<Customers>> getAllCustomers() throws SQLException {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    public ResponseEntity<Customers> getCustomerById(int customerId) throws SQLException {
        return ResponseEntity.status(200).body(customerService.getCustomerById(customerId));
    }

    public ResponseEntity<Integer> addCustomer(Customers customers) throws SQLException {
        return ResponseEntity.status(200).body(customerService.addCustomer(customers));
    }

    public ResponseEntity<Void> updateCustomer(int customerId, Customers customers) throws SQLException {
        customerService.updateCustomer(customers);
        return ResponseEntity.status(200).build();
    }
    public ResponseEntity<Void> deleteCustomer(int customerId) {
        return ResponseEntity.status(200).build();    }

    public ResponseEntity<List<Transactions>> getAllTransactionsByCustomerId(int customerId) {
        return null;
    }
}
