package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDAO {

    // DEPOSIT
    public static void deposit(int accountId, double amount) {
        try {
            Connection con = DBconnection.getConnection();

            double currentBalance = AccountDAO.getBalance(accountId);
            double newBalance = currentBalance + amount;

            AccountDAO.updateBalance(accountId, newBalance);

            String query = "INSERT INTO transactions (account_id, type, amount) VALUES (?, 'DEPOSIT', ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, accountId);
            ps.setDouble(2, amount);

            ps.executeUpdate();

            System.out.println("Deposit successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // WITHDRAW
    public static void withdraw(int accountId, double amount) {
        try {
            Connection con = DBconnection.getConnection();

            double currentBalance = AccountDAO.getBalance(accountId);

            if (currentBalance < amount) {
                System.out.println("Insufficient balance!");
                return;
            }

            double newBalance = currentBalance - amount;
            AccountDAO.updateBalance(accountId, newBalance);

            String query = "INSERT INTO transactions (account_id, type, amount) VALUES (?, 'WITHDRAW', ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, accountId);
            ps.setDouble(2, amount);

            ps.executeUpdate();

            System.out.println("Withdrawal successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}