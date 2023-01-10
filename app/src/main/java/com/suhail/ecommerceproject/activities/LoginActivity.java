package com.suhail.ecommerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.databinding.ActivityLoginBinding;
import com.suhail.ecommerceproject.models.User;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    ProductDB db ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new ProductDB(this);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.etUserName.getText().toString();
                String password = binding.etPassword.getText().toString();
                if(!checkFields())
                    Toast.makeText(LoginActivity.this, "Please enter all required fields!", Toast.LENGTH_SHORT).show();
                else{
                    User user = new User(userName,password);
                    if(db.login(user)==true)
                    {
                        if(binding.rbAdmin.isChecked())
                            startActivity(new Intent(getBaseContext(),AdminActivity.class));
                        else
                            startActivity(new Intent(getBaseContext(),UserActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Wrong UserName or Passowrd!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                    }
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),SignUpActivity.class));
            }
        });
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