package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService transactionService; // JPA service via interface

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // GET /transactions
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        try {
            return ResponseEntity.ok(transactionService.getAllTransactions());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET /transactions/{transactionId}
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable int transactionId) {
        try {
            Transactions t = transactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(t);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST /transactions  -> return 201 Created + Location header + id in body
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addTransaction(@RequestBody Transactions transaction) {
        try {
            int id = transactionService.addTransaction(transaction);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/transactions/" + id));
            return new ResponseEntity<>(id, headers, HttpStatus.CREATED);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT /transactions/{transactionId}
    @PutMapping(value = "/{transactionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTransaction(@PathVariable int transactionId,
                                                  @RequestBody Transactions transaction) {
        try {
            transaction.setTransactionId(transactionId);
            transactionService.updateTransaction(transaction);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE /transactions/{transactionId}
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET /transactions/user/{customerId}  (used later; safe to include now)
    @GetMapping("/user/{customerId}")
    public ResponseEntity<List<Transactions>> getAllTransactionsByCustomerId(@PathVariable int customerId) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(customerId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}