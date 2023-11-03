package com.inventorymanagement.items;

public class FragileItem extends InventoryItem {
    private int weigth;

    public FragileItem(String category, boolean breakable, boolean perishable, boolean sellable, int itemID, int quantity, String name, double price) {
        super(category, breakable, perishable, sellable, itemID, quantity, name, price);
    }


    public int getWeigth() {
        return weigth;
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


    @Override
    public void setCategory(String category) {

    }
}
