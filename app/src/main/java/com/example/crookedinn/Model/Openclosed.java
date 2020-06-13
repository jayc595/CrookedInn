package com.example.crookedinn.Model;

public class Openclosed {
    private String bar, barmenu, lunchmenu, specialsmenu;

    public Openclosed(){

    }

    public Openclosed(String bar, String barmenu, String lunchmenu, String specialsmenu) {
        this.bar = bar;
        this.barmenu = barmenu;
        this.lunchmenu = lunchmenu;
        this.specialsmenu = specialsmenu;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getBarmenu() {
        return barmenu;
    }

    public void setBarmenu(String barmenu) {
        this.barmenu = barmenu;
    }

    public String getLunchmenu() {
        return lunchmenu;
    }

    public void setLunchmenu(String lunchmenu) {
        this.lunchmenu = lunchmenu;
    }

    public String getSpecialsmenu() {
        return specialsmenu;
    }

    public void setSpecialsmenu(String specialsmenu) {
        this.specialsmenu = specialsmenu;
    }
}

