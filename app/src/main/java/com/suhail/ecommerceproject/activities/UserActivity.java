package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.adapters.AllProductsRVAdapter;
import com.suhail.ecommerceproject.databinding.ActivityUserBinding;
import com.suhail.ecommerceproject.models.Product;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ActivityUserBinding binding;
    ProductDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new ProductDB(this);
        initRv();
        binding.btnShowCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),CartActivity.class));
            }
        });
    }
    private void initRv(){
        ArrayList<Product> products = db.getAllProducts();
        AllProductsRVAdapter adapter = new AllProductsRVAdapter(products,this);
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProducts.setAdapter(adapter);
        binding.rvProducts.setHasFixedSize(true);
    }
}