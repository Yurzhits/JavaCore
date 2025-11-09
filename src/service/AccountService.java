package service;

import exception.InvalidAccountException;
import model.Account;

public class AccountService {
    public void validateAccount(String accountNumber) throws InvalidAccountException {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new InvalidAccountException("Номер счета не может быть пустым");
        }

        if (!accountNumber.matches("\\d{5}-\\d{5}")) {
            throw new InvalidAccountException("Неверный формат номера счета: " + accountNumber);
        }
    }
}
