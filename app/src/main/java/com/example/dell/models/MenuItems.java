package com.example.dell.models;

import java.util.List;

public class MenuItems {
    private List<String> item;
    private List<String> price;

    public MenuItems(List<String> item, List<String> price) {
        this.item = item;
        this.price = price;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }
}
