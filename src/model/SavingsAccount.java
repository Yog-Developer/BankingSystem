package model;

import exception.InsufficientFundsException;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04; // 4% annual interest
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountId, String ownerId, double initialBalance) {
        super(accountId, ownerId, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        addTransaction("DEPOSIT | Rs. " + amount + " | Balance: Rs. " + balance);
        System.out.println("Deposited Rs. " + amount + " into Savings Account.");
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (balance - amount < MIN_BALANCE) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
        addTransaction("WITHDRAWAL | Rs. " + amount + " | Balance: Rs. " + balance);
        System.out.println("Withdrawn Rs. " + amount + " from Savings Account.");
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        addTransaction("INTEREST APPLIED | Rs. " + interest + " | Balance: Rs. " + balance);
        System.out.println("Interest of Rs. " + interest + " applied.");
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}