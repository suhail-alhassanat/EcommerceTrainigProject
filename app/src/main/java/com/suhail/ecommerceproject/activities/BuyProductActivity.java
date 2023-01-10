package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.suhail.ecommerceproject.databinding.ActivityBuyProductBinding;
import com.suhail.ecommerceproject.helper.Cart;
import com.suhail.ecommerceproject.models.CartItem;
import com.suhail.ecommerceproject.models.Product;

public class BuyProductActivity extends AppCompatActivity {
ActivityBuyProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if(intent != null){
            Product product = (Product) intent.getSerializableExtra("product");
            binding.tvId.setText(String.valueOf(product.getId()));
            binding.tvName.setText(product.getName());
            binding.tvDetails.setText(product.getDetails());
            binding.tvPrice.setText(String.valueOf(product.getPrice()));
            binding.tvTotalPrice.setText(String.valueOf(product.getPrice()));
        }
        binding.etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String quantityStr = binding.etQuantity.getText().toString();
                if(!quantityStr.isEmpty()){
                 double quantity = Double.parseDouble(quantityStr);
                 double price = Double.parseDouble(binding.tvPrice.getText().toString());
                 binding.tvTotalPrice.setText(String.valueOf(quantity * price));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.etQuantity.getText().toString().isEmpty()){
                    String name = binding.tvName.getText().toString();
                    String details = binding.tvDetails.getText().toString();
                    double price = Double.parseDouble(binding.tvPrice.getText().toString());
                    double totalPrice = Double.parseDouble(binding.tvTotalPrice.getText().toString());
                    int quantity = Integer.parseInt(binding.etQuantity.getText().toString());
                    CartItem item = new CartItem(name,totalPrice,details,quantity,price);
                    Cart.addToCart(item);
                    Toast.makeText(BuyProductActivity.this, "Added to cart Successfully..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}