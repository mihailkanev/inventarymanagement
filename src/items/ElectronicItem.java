package com.inventorymanagement.items;

public class ElectronicItem extends InventoryItem {
    private int warrantyMonths;

    public ElectronicItem(String category, boolean breakable, boolean perishable, boolean sellable, int itemID, int quantity, String name, double price) {
        super(category, breakable, perishable, sellable, itemID, quantity, name, price);
    }


    @Override
    public void handleBreakage() {

    }

    public String setCategory() {
        return null;
    }

    @Override
    public boolean isPerish() {
        return false;
    }

    @Override
    public void handlePerishExpiration() {

    }

    @Override
    public void getPriceForSellable() {

    }

    @Override
    public void setPriceForSellable() {

    }

    @Override
    public String getItemDetails() {
        return null;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void setCategory(String category) {

    }
}


