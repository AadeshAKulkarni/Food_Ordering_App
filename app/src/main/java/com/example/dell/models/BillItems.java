package com.example.dell.models;

/**
 * Created by Dell on 10/24/2016.
 */

public class BillItems {
    int id;
    String itemname;
    String qty;
    String price;

    public BillItems(int id, String itemname, String price, String qty) {
        this.id=id;
        this.itemname = itemname;
        this.price = price;
        this.qty = qty;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
