package service;

import exception.InvalidAmountException;

public class AmountValidator {

    public double parseAmount(String amountStr) throws InvalidAmountException {
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new InvalidAmountException("Неверный формат суммы: " + amountStr);
        }
    }

    public void validateAmount(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Сумма перевода должна быть положительной: " + amount);
        }

        if (amount != Math.floor(amount)) {
            throw new InvalidAmountException("Сумма перевода должна быть целым числом: " + amount);
        }
    }
}