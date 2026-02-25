package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Accounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        return null;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Accounts> getAccountById(@PathVariable int accountId) {
        return null;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Accounts>> getAccountsByUser(@PathVariable("userId") String param) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Integer> addAccount(@RequestBody Accounts accounts) {
        return null;
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable int accountId, @RequestBody Accounts accounts) {
        return null;
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int accountId) {
        return null;
    }
}