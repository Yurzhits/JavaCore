package model;

import java.time.LocalDateTime;

public class Transaction {
    private String fileName;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public Transaction(String message, String fileName, String fromAccount, String toAccount,
                       double amount, String status, LocalDateTime timestamp) {
        this.message = message;
        this.fileName = fileName;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " | "+ fileName +
                " | перевод с " + fromAccount +
                " | на '" + toAccount +
                " " + amount +
                " | " + status +
                " | " + message + '\'';
    }
}
