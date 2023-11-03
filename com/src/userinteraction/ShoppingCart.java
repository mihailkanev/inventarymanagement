package com.inventorymanagement.userinteraction;
import com.inventorymanagement.order.OrderItem;
import com.inventorymanagement.items.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<OrderItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addOrderItem(InventoryItem inventoryItem, int quantity) {
        items.add(new OrderItem(inventoryItem, quantity));
    }
    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
    }

    public void clear() {
        items.clear();
    }
}
