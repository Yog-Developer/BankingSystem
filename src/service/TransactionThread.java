package service;

import exception.InsufficientFundsException;
import model.Account;

public class TransactionThread implements Runnable {

    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    private Account account;
    private double amount;
    private TransactionType type;

    public TransactionThread(Account account, double amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public void run() {
        // synchronized block prevents two threads corrupting balance at the same time
        synchronized (account) {
            try {
                if (type == TransactionType.DEPOSIT) {
                    account.deposit(amount);
                } else if (type == TransactionType.WITHDRAW) {
                    account.withdraw(amount);
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Transaction failed for account "
                    + account.getAccountId() + ": " + e.getMessage());
            }
        }
    }
}