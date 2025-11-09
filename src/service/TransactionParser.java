package service;

import model.Transaction;
import exception.InvalidAccountException;
import exception.InvalidAmountException;
import java.time.LocalDateTime;

public class TransactionParser {

    private final AccountService accountService;
    private final AmountValidator amountValidator;

    public TransactionParser() {
        this.accountService = new AccountService();
        this.amountValidator = new AmountValidator();
    }

    public Transaction parseTransactionLine(String fileName, String line, int lineNumber)
            throws InvalidAccountException, InvalidAmountException {

        String[] parts = line.split("\\s+");

        if (parts.length < 3) {
            throw new InvalidAmountException("Недостаточно данных");
        }

        String fromAccount = parts[0];
        String toAccount = parts[1];
        String amountStr = parts[2];

        accountService.validateAccount(fromAccount);
        accountService.validateAccount(toAccount);

        double amount = amountValidator.parseAmount(amountStr);
        amountValidator.validateAmount(amount);

        return new Transaction("успешно обработан", fileName, fromAccount, toAccount,
                amount, "успешно", LocalDateTime.now());
    }

    public Transaction createErrorTransaction(String fileName, int lineNumber, String errorMessage) {
        return new Transaction("строка " + lineNumber + ": " + errorMessage, fileName, "", "",
                0, "ошибка", LocalDateTime.now());
    }
}