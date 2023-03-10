package com.suhail.ecommerceproject.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private String details;

    public Product(int id, String name, double price, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public Product(String name, double price, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
