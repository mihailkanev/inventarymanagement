package com.inventorymanagement.items;

public abstract class InventoryItem extends Item {
    private int itemID;
    private int quantity;
    private String name;
    private double price;


    public InventoryItem(String category, boolean breakable, boolean perishable, boolean sellable, int itemID, int quantity, String name, double price) {
        super(category, breakable, perishable, sellable);
        this.itemID = itemID;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }


    public void reduceQuantity(int quantityToReduce) {
        if (quantityToReduce >= 0 && quantityToReduce <= this.quantity) {
            this.quantity -= quantityToReduce;
        }
    }
}