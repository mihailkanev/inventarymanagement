package com.inventorymanagement.items;

public class GeneralInventoryItem extends InventoryItem{
    public GeneralInventoryItem(String category, boolean breakable, boolean perishable, boolean sellable, int itemID, int quantity, String name, double price) {
        super(category, breakable, perishable, sellable, itemID, quantity, name, price);
    }

    @Override
    public void handleBreakage() {

    }

    @Override
    public void setCategory(String category) {

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


    public String setCategory() {
        return null;
    }

    @Override
    public String getItemDetails() {
        return null;
    }
}
