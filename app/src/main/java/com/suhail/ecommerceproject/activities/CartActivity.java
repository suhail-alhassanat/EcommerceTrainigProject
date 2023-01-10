package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.suhail.ecommerceproject.adapters.CartItemsRVAdapter;
import com.suhail.ecommerceproject.databinding.ActivityCartBinding;
import com.suhail.ecommerceproject.helper.Cart;

public class CartActivity extends AppCompatActivity {
ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CartItemsRVAdapter adapter = new CartItemsRVAdapter(Cart.getCart(),this);
        binding.rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCartItems.setAdapter(adapter);
        binding.rvCartItems.setHasFixedSize(true);
    }
}