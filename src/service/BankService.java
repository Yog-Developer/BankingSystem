package service;

import exception.InsufficientFundsException;
import model.Account;
import model.Customer;
import service.TransactionThread.TransactionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankService {
    private Map<String, Customer> customerMap = new HashMap<>();

    public void registerCustomer(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
        System.out.println("Customer registered: " + customer.getName());
    }

    public Customer getCustomer(String customerId) {
        return customerMap.get(customerId);
    }

    public boolean login(String customerId, String password) {
        Customer c = customerMap.get(customerId);
        if (c != null && c.validatePassword(password)) {
            System.out.println("Login successful. Welcome, " + c.getName());
            return true;
        }
        System.out.println("Invalid ID or password.");
        return false;
    }

    // Transfer between two accounts using threads
    public void transfer(Account from, Account to, double amount) {
        System.out.println("Initiating transfer of Rs. " + amount + "...");

        Thread withdrawThread = new Thread(
            new TransactionThread(from, amount, TransactionType.WITHDRAW)
        );
        Thread depositThread = new Thread(
            new TransactionThread(to, amount, TransactionType.DEPOSIT)
        );

        withdrawThread.start();
        try { withdrawThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        depositThread.start();
        try { depositThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Transfer complete.");
    }

    // Lambda usage — filter accounts above a minimum balance
    public List<Account> getAccountsAboveBalance(Customer customer, double minBalance) {
        return customer.getAllAccounts()
                .stream()
                .filter(acc -> acc.getBalance() > minBalance)
                .collect(Collectors.toList());
    }
}