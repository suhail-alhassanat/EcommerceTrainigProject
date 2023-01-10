package com.suhail.ecommerceproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suhail.ecommerceproject.DB.ProductDB;
import com.suhail.ecommerceproject.activities.BuyProductActivity;
import com.suhail.ecommerceproject.databinding.CartItemCustomLayoutBinding;
import com.suhail.ecommerceproject.databinding.ProductCustomLayoutForUserBinding;
import com.suhail.ecommerceproject.helper.Cart;
import com.suhail.ecommerceproject.models.Product;

import java.util.ArrayList;

public class AllProductsRVAdapter extends RecyclerView.Adapter<AllProductsRVAdapter.AllProductsViewHolder>{
    ArrayList<Product> products;
    ProductCustomLayoutForUserBinding binding;
    Context context;

    public AllProductsRVAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public AllProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllProductsViewHolder(ProductCustomLayoutForUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        binding.tvName.setText(products.get(position).getName());
        binding.tvDetails.setText(products.get(position).getDetails());
        binding.tvPrice.setText(String.valueOf(products.get(position).getPrice()));
        binding.tvId.setText(String.valueOf(products.get(position).getId()));
        binding.btnBuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product",products.get(position));
                context.startActivity(new Intent(context, BuyProductActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class AllProductsViewHolder extends RecyclerView.ViewHolder{

        public AllProductsViewHolder(@NonNull ProductCustomLayoutForUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
