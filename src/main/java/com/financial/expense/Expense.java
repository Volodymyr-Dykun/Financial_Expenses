package com.financial.expense;

public class Expense {

    private String date;
    private String name;
    private double price;
    private String currency;

    public Expense() {
    }

    public Expense(String date, double price, String currency, String name) {
        this.date = date;
        this.price = price;
        this.currency = currency;
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
