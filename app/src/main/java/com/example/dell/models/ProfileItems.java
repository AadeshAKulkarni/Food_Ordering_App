package com.example.dell.models;

import java.util.List;

public class ProfileItems {
    private List<String> account;
    private List<String> contact;

    public ProfileItems(List<String> item, List<String> price) {
        this.account= item;
        this.contact = price;
    }

    public List<String> getItem() {
        return account;
    }

    public void setItem(List<String> item) {
        this.account = item;
    }

    public List<String> getPrice() {
        return contact;
    }

    public void setPrice(List<String> price) {
        this.contact = price;
    }
}
