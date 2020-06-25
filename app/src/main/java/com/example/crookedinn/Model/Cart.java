package com.example.crookedinn.Model;

public class Cart {
    private String pid, iname, price, quantity, discount, category, addon;

    public Cart() {

    }

    public Cart(String pid, String iname, String price, String quantity, String discount, String category, String addon) {
        this.pid = pid;
        this.iname = iname;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.category = category;
        this.addon = addon;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }
}

