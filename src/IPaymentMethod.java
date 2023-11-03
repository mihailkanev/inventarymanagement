package com.inventorymanagement;

public interface IPaymentMethod {
    boolean processPayment(double amount);
    String getDescription();
}
