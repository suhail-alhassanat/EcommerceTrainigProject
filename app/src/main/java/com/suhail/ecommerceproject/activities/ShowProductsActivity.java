package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.adapters.ProductRVAdapter;
import com.suhail.ecommerceproject.databinding.ActivityShowProductsBinding;
import com.suhail.ecommerceproject.listeners.OnDeleteProductClickListerner;
import com.suhail.ecommerceproject.listeners.OnUpdateProductClickListerner;
import com.suhail.ecommerceproject.models.Product;

import java.util.ArrayList;

public class ShowProductsActivity extends AppCompatActivity {
ActivityShowProductsBinding binding;
ProductDB db;
ProductRVAdapter adapter ;
ArrayList<Product> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowProductsBinding.inflate(getLayoutInflater());
        db = new ProductDB(this);
        setContentView(binding.getRoot());
        initRv();

    }
    public void initRv(){
        products = db.getAllProducts();
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new ProductRVAdapter(products, new OnUpdateProductClickListerner() {
            @Override
            public void onUpdateClicked(Product product) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product",product);
                startActivity(new Intent(getBaseContext(),UpdateProductActivity.class).putExtras(bundle));
            }
        }, new OnDeleteProductClickListerner() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDeleteClicked(int id) {
                Toast.makeText(ShowProductsActivity.this, id+"", Toast.LENGTH_SHORT).show();
                if(db.deleteProduct(id)){
                    Toast.makeText(ShowProductsActivity.this, "Product Deleted Successfully..", Toast.LENGTH_SHORT).show();
                    initRv();
                }
            }
        });
        binding.rvProducts.setAdapter(adapter);
        binding.rvProducts.setHasFixedSize(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initRv();
    }
}