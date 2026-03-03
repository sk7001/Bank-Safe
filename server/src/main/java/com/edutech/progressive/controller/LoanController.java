
package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Loan;
import com.edutech.progressive.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // GET /loans -> 200 OK
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    // GET /loans/{id} -> 200 OK or 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        if (loan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(loan);
    }

    // POST /loans -> 201 Created (+ Location + created body)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan created = loanService.createLoan(loan);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created); // 201
    }

    // PUT /loans/{id} -> 200 OK (return updated entity or no body per test)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        loan.setId(id);
       loanService.updateLoan(loan);
        return ResponseEntity.ok().build(); // 200
    }

    // DELETE /loans/{id} -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build(); // 204
    }
}