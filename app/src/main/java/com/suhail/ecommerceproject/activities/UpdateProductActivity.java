package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.databinding.ActivityUpdateProductBinding;
import com.suhail.ecommerceproject.models.Product;

public class UpdateProductActivity extends AppCompatActivity {
ActivityUpdateProductBinding binding;
ProductDB db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new ProductDB(this);
        Intent intent = getIntent();
        if(intent != null){
            Product product = (Product) intent.getExtras().getSerializable("product");
            binding.etProductId.setText(String.valueOf(product.getId()));
            binding.etProductName.setText(product.getName());
            binding.etProductDetails.setText(product.getDetails());
            binding.etProductPrice.setText(String.valueOf(product.getPrice()));
        }
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFields()){
                Product product = new Product();
                product.setName(binding.etProductName.getText().toString());
                product.setDetails(binding.etProductDetails.getText().toString());
                product.setPrice(Double.parseDouble(binding.etProductPrice.getText().toString()));
                product.setId(Integer.parseInt(binding.etProductId.getText().toString()));
                if(db.updateProduct(product))
                    Toast.makeText(UpdateProductActivity.this, "Updated Successfully..", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(UpdateProductActivity.this, "Enter All Fields Please...", Toast.LENGTH_SHORT).show();
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

}