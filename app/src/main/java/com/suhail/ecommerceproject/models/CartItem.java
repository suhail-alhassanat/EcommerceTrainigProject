package com.suhail.ecommerceproject.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private double totalPrice;
    private double price;
    private String details;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(String name, double totalPrice, String details, int quantity, double price) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.details = details;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
