package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suhail.ecommerceproject.databinding.ActivityAdminBinding;
import com.suhail.ecommerceproject.databinding.ActivityLoginBinding;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnInsertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),InsertProductActivity.class));
            }
        });
        binding.btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ShowProductsActivity.class));
            }
        });
    }
}