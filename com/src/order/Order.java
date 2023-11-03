package com.inventorymanagement.order;

import com.inventorymanagement.IPaymentMethod;
import com.inventorymanagement.items.InventoryItem;
import com.inventorymanagement.userinteraction.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final IPaymentMethod paymentMethod;
    private int orderID;
    private List<InventoryItem> items;
    private List<OrderItem> orderItems;

    private double totalCost;
    private ShoppingCart shoppingCart;

    public Order(int orderID, List<OrderItem> orderItems, double totalCost, IPaymentMethod paymentMethod) {
        this.orderID = orderID;
        this.orderItems = orderItems != null ? orderItems : new ArrayList<>();
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.shoppingCart = new ShoppingCart();
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void addOrderItem(InventoryItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        orderItems.add(orderItem);
        shoppingCart.addItem(orderItem);
        totalCost += orderItem.calculateCost();
    }


    public IPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void clearShoppingCart() {
        shoppingCart.clear();
        this.totalCost = 0;
    }

}