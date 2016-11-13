package com.example.dell.models;

/**
 * Created by Dell on 10/24/2016.
 */

public class CartItems {
    int id;
    String itemname;
    String qty;
    String price;
    String del;
    public CartItems(int id,String itemname, String price, String qty,String del ) {
        this.id=id;
        this.itemname = itemname;
        this.price = price;
        this.qty = qty;
        this.del=del;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
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
