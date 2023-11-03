package com.inventorymanagement.order;

import com.inventorymanagement.items.InventoryItem;

public class OrderItem {
    private InventoryItem inventoryItem;
    private int quantity;

    public OrderItem(InventoryItem inventoryItem, int quantity) {
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double calculateCost(){
        return this.quantity * inventoryItem.getPrice();
    }
}
