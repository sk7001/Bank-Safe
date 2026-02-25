package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        return null;
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable int transactionId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Integer> addTransaction(@RequestBody Transactions transaction) {
        return null;
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transaction) {
        return null;
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        return null;
    }
}
