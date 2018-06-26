package com.financial;

import java.time.LocalDate;

/**
 * Created by Volodymyr Dykun on 21.06.2018.
 */
public class Expense {

    private LocalDate date;
    private String name;
    private Double price;
    private String currency;

    public Expense() {
    }
    public Expense(String date, Double price, String currency, String name) {
        this.date     = LocalDate.parse(date);
        this.price    = price;
        this.currency = currency;
        this.name     = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}