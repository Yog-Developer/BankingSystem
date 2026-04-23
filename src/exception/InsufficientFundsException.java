package exception;

public class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Attempted to withdraw: Rs. " + amount);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}