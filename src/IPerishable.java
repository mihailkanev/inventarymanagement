package com.inventorymanagement;

public interface IPerishable {
    boolean isPerish();
    void handlePerishExpiration();
}
