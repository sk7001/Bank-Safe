package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.impl.CustomerServiceImplArraylist;
import com.edutech.progressive.service.impl.CustomerServiceImplJpa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImplJpa customerServiceJpa;
    private final CustomerServiceImplArraylist customerServiceArraylist;

    public CustomerController(CustomerServiceImplJpa customerServiceJpa,
                              CustomerServiceImplArraylist customerServiceArraylist) {
        this.customerServiceJpa = customerServiceJpa;
        this.customerServiceArraylist = customerServiceArraylist;
    }

    // ===================== JPA Endpoints =====================

    /**
     * GET /customers
     * Returns a list of all customers from the database (JPA).
     */
    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerServiceJpa.getAllCustomers());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /customers/{customerId}
     * Retrieves a customer by ID from the database (JPA).
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable int customerId) {
        try {
            Customers c = customerServiceJpa.getCustomerById(customerId);
            return ResponseEntity.ok(c);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PostMapping
    public ResponseEntity<Integer> addCustomer(@RequestBody Customers customers) {
        try {
            int id = customerServiceJpa.addCustomer(customers);
            
            return ResponseEntity.ok(id);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable int customerId, @RequestBody Customers customers) {
        try {
            customers.setCustomerId(customerId);
            customerServiceJpa.updateCustomer(customers);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

   
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        try {
            customerServiceJpa.deleteCustomer(customerId);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }




    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Customers>> getAllCustomersFromArrayList() {
        try {
            return ResponseEntity.ok(customerServiceArraylist.getAllCustomers());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @GetMapping("/fromArrayList/all")
    public ResponseEntity<List<Customers>> getAllCustomersSortedByNameFromArrayList() {
        try {
            return ResponseEntity.ok(customerServiceArraylist.getAllCustomersSortedByName());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addCustomersToArrayList(@RequestBody Customers customers) {
        try {
            int result = customerServiceArraylist.addCustomer(customers);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


   
    @GetMapping("/{customerId}/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactionsByCustomerId(@PathVariable int customerId) {
        return ResponseEntity.ok(null);
    }
}