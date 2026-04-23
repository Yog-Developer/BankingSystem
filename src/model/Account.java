package model;

import exception.InsufficientFundsException;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String accountId;
    private String ownerId;
    protected double balance;
    private List<String> transactionHistory;

    public Account(String accountId, String ownerId, double initialBalance) {
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Abstract methods — every subclass MUST implement these
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException;
    public abstract String getAccountType();

    // Common method for all accounts
    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    protected void addTransaction(String record) {
        transactionHistory.add(record);
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + " | Type: " + getAccountType() + " | Balance: Rs. " + balance;
    }
}