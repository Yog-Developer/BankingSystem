import db.DBconnection;

public class Testconnection {
    public static void main(String[] args) {
        if (DBconnection.getConnection() != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}