package com.suhail.ecommerceproject.helper;

import com.suhail.ecommerceproject.models.CartItem;

import java.util.ArrayList;

public class Cart {
    private static ArrayList<CartItem> cart = new ArrayList<>();

public static void addToCart(CartItem item){
    cart.add(item);
}
    public static void removeFromCart(CartItem item){
        cart.remove(item);
    }
    public static ArrayList<CartItem> getCart(){
    return cart;
    }
}
