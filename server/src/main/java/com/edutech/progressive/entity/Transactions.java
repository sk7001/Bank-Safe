package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Column(nullable = false)
    private double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    /**
     * Association to Accounts. We keep FK field (accountId) as the write source of truth.
     * insertable/updatable=false to avoid column write conflicts.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Accounts accounts;

    public Transactions() {}

    public Transactions(int transactionId, int accountId, double amount, Date transactionDate, String transactionType) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }

    public Accounts getAccounts() { return accounts; }

    /**
     * Day 9 tests call setAccounts(...). We intentionally do not auto-sync FK here.
     */
    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
}