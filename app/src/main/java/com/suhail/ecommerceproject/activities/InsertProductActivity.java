package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.databinding.ActivityInsertProductBinding;
import com.suhail.ecommerceproject.models.Product;

public class InsertProductActivity extends AppCompatActivity {
ActivityInsertProductBinding binding;
ProductDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new ProductDB(this);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkFields()){
                    Toast.makeText(InsertProductActivity.this, "Enter all fields please..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name = binding.etProductName.getText().toString();
                    String details = binding.etProductDetails.getText().toString();
                    double price = Double.parseDouble(binding.etProductPrice.getText().toString());
                    Product product = new Product(name,price,details);
                    if(db.insertProduct(product)){
                        Toast.makeText(InsertProductActivity.this, "Product Inserted Succefully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                    else
                        Toast.makeText(InsertProductActivity.this, "something wrong!\nTry again..", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    boolean checkFields(){
        if(binding.etProductName.getText().toString().isEmpty()||binding.etProductPrice.getText().toString().isEmpty()||binding.etProductDetails.getText().toString().isEmpty())
            return false;
        else
            return true;
    }
    void clearFields(){
        binding.etProductDetails.setText("");
        binding.etProductPrice.setText("");
        binding.etProductName.setText("");
    }
}