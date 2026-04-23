import db.AccountDAO;
import db.TransactionDAO;

public class Main {
    public static void main(String[] args) {

        // Create account
        AccountDAO.createAccount("Lakshya", 5000);

        // Deposit
        TransactionDAO.deposit(1, 2000.0);
        

        // Withdraw
        TransactionDAO.withdraw(1, 1000.0);

        // Final Balance
        double bal = AccountDAO.getBalance(1);
        System.out.println("Final Balance: " + bal);
        System.out.println("Update for PR");
    }
}