package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String password;
    private List<Account> accounts;
    private Map<String, Account> accountMap; // For fast lookup by account ID

    public Customer(String customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.accountMap = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        accountMap.put(account.getAccountId(), account);
        System.out.println(getAccountType(account) + " account added for " + name);
    }

    private String getAccountType(Account account) {
        return account.getAccountType();
    }

    public Account getAccountById(String accountId) {
        return accountMap.get(accountId); // O(1) lookup using HashMap
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + " | Name: " + name + " | Email: " + email;
    }
}