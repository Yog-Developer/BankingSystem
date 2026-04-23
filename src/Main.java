import exception.InsufficientFundsException;
import model.*;
import service.*;
import service.TransactionThread.TransactionType;

public class Main {
    public static void main(String[] args) {

        // Create customer
        Customer customer = new Customer("C001", "Rahul Sharma", "rahul@email.com", "pass123");

        // Create accounts
        SavingsAccount savings = new SavingsAccount("ACC001", "C001", 5000);
        CurrentAccount current = new CurrentAccount("ACC002", "C001", 10000);

        customer.addAccount(savings);
        customer.addAccount(current);

        // Test deposit and withdraw
        savings.deposit(2000);
        try {
            savings.withdraw(1000);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test custom exception
        try {
            savings.withdraw(999999); // Should throw exception
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // Test multithreading via BankService transfer
        BankService bank = new BankService();
        bank.registerCustomer(customer);
        bank.transfer(current, savings, 2000);

        // Print balances
        System.out.println(savings);
        System.out.println(current);

        // Print transaction history using lambda
        System.out.println("\n--- Savings Transaction History ---");
        savings.getTransactionHistory().forEach(System.out::println);
    }
}