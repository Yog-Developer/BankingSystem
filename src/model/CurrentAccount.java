package model;

import exception.InsufficientFundsException;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000.0; // Can go negative up to this

    public CurrentAccount(String accountId, String ownerId, double initialBalance) {
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
        System.out.println("Deposited Rs. " + amount + " into Current Account.");
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
        addTransaction("WITHDRAWAL | Rs. " + amount + " | Balance: Rs. " + balance);
        System.out.println("Withdrawn Rs. " + amount + " from Current Account.");
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}