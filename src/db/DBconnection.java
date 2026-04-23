package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/banking_system",
                "root",
                "root123"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}