package com.inventorymanagement.payment;

import com.inventorymanagement.IPaymentMethod;

public class CreditCardPayment implements IPaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String securityCode;

    public CreditCardPayment(String cardNumber, String cardHolder, String expirationDate, String securityCode) {
        if (isValidCardInfo(cardNumber, cardHolder, expirationDate, securityCode)) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
            this.expirationDate = expirationDate;
            this.securityCode = securityCode;
        } else {
            throw new IllegalArgumentException("Invalid credit card information. All fields must be non-empty strings.");
        }
    }

    private boolean isValidCardInfo(String cardNumber, String cardHolder, String expirationDate, String securityCode) {
        return cardNumber != null && !cardNumber.isEmpty() &&
                cardHolder != null && !cardHolder.isEmpty() &&
                expirationDate != null && !expirationDate.isEmpty() &&
                securityCode != null && !securityCode.isEmpty();
    }

    @Override
    public boolean processPayment(double amount) {
        boolean paymentSuccessful = simulatePayPalPayment(amount);
        return paymentSuccessful;
    }

    private boolean simulatePayPalPayment(double amount) {
        return true;
    }

    @Override
    public String getDescription() {
        return "Credit Card Payment";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
