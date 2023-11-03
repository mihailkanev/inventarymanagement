package com.inventorymanagement.payment;

import com.inventorymanagement.IPaymentMethod;

public class PaymentManager {
    private IPaymentMethod paymentMethod;

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean processPayment(double amount) {
        if (paymentMethod != null) {
            return paymentMethod.processPayment(amount);
        } else {
            System.out.println("No payment method selected.");
            return false;
        }
    }
}
