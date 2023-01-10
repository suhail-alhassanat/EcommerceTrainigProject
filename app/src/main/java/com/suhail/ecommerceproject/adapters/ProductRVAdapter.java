package com.suhail.ecommerceproject.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suhail.ecommerceproject.databinding.ProductCustomLayoutBinding;
import com.suhail.ecommerceproject.listeners.OnDeleteProductClickListerner;
import com.suhail.ecommerceproject.listeners.OnUpdateProductClickListerner;
import com.suhail.ecommerceproject.models.Product;

import java.util.ArrayList;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>{
ProductCustomLayoutBinding binding;
ArrayList<Product> products;
OnUpdateProductClickListerner updateListener;
OnDeleteProductClickListerner deleteListener;

    public ProductRVAdapter(ArrayList<Product> products, OnUpdateProductClickListerner updateListener, OnDeleteProductClickListerner deleteListener) {
        this.products = products;
        this.updateListener = updateListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(ProductCustomLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        binding.tvName.setText(products.get(position).getName());
        binding.tvDetails.setText(products.get(position).getDetails());
        binding.tvPrice.setText(String.valueOf(products.get(position).getPrice()));
        binding.tvId.setText(String.valueOf(products.get(position).getId()));
        binding.btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteClicked(products.get(position).getId());
            }
        });
        binding.btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListener.onUpdateClicked(products.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public ProductViewHolder(@NonNull ProductCustomLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
