package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.databinding.ActivitySignUpBinding;
import com.suhail.ecommerceproject.models.User;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
ProductDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new ProductDB(this);
        binding.btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.etUserName.getText().toString();
                String password = binding.etPassword.getText().toString();
                if(!checkFields())
                    Toast.makeText(getBaseContext(), "Please enter all required fields!", Toast.LENGTH_SHORT).show();
                else{
                    User user = new User(userName,password);
                    if(db.createUser(user)==true)
                    {
                        Toast.makeText(SignUpActivity.this, "User Created Succefully..", Toast.LENGTH_SHORT).show();
                        clearFields();
                        }
                    else {
                        Toast.makeText(getBaseContext(), "something Wrong!..Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
        );
    }
    boolean checkFields(){
        if (binding.etUserName.getText().toString().isEmpty() || binding.etPassword.getText().toString().isEmpty())
            return false;
        else
            return true;
    }
    void clearFields(){
    binding.etPassword.setText("");
    binding.etUserName.setText("");
    }
}