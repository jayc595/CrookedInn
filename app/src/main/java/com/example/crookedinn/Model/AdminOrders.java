package com.example.crookedinn.Model;

public class AdminOrders {
    private String date, notes, state, tableNumber, time, totalAmount, phone;

    public AdminOrders() {
    }

    public AdminOrders(String date, String notes, String state, String tableNumber, String time, String totalAmount, String phone) {
        this.date = date;
        this.notes = notes;
        this.state = state;
        this.tableNumber = tableNumber;
        this.time = time;
        this.totalAmount = totalAmount;
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
