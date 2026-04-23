package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    // CREATE ACCOUNT
    public static void createAccount(String name, double balance) {
        try {
            Connection con = DBconnection.getConnection();

            String query = "INSERT INTO accounts (name, balance) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setDouble(2, balance);

            ps.executeUpdate();

            System.out.println("Account created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET BALANCE
    public static double getBalance(int id) {
        try {
            Connection con = DBconnection.getConnection();

            String query = "SELECT balance FROM accounts WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // UPDATE BALANCE
    public static void updateBalance(int id, double newBalance) {
        try {
            Connection con = DBconnection.getConnection();

            String query = "UPDATE accounts SET balance=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, newBalance);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}