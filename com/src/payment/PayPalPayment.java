package com.inventorymanagement.payment;

import com.inventorymanagement.IPaymentMethod;

public class PayPalPayment implements IPaymentMethod {
    private String paypalAccount;

    public PayPalPayment(String paypalAccount) {
        if (isValidPayPalAccount(paypalAccount)) {
            this.paypalAccount = paypalAccount;
        } else {
            throw new IllegalArgumentException("Invalid PayPal account. The account must be a non-empty string.");
        }
    }

    private boolean isValidPayPalAccount(String account) {
        return account != null && !account.isEmpty();
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
        return "PayPal Payment";
    }
}
