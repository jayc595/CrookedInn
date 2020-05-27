package com.example.crookedinn.Model;

public class Products {
    private String iname, price, category, description, pid, date, time, gf, df, stock;




    public Products(){

    }
    public Products(String iname, String price, String category, String description, String pid, String date, String time, String gf, String df, String stock) {
        this.iname = iname;
        this.price = price;
        this.category = category;
        this.description = description;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.gf = gf;
        this.df = df;
        this.stock = stock;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
