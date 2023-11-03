package com.inventorymanagement;

public interface ICategorisable {

    String getCategory();
    void setCategory(String category);

    interface IItem {
        String getItemDetails();

        int calculateValue();

        String itemDescription();
    }
}
