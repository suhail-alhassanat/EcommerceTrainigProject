package com.suhail.ecommerceproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suhail.ecommerceproject.databinding.CartItemCustomLayoutBinding;
import com.suhail.ecommerceproject.helper.Cart;
import com.suhail.ecommerceproject.models.CartItem;

import java.util.ArrayList;

public class CartItemsRVAdapter extends RecyclerView.Adapter<CartItemsRVAdapter.CartItemsViewHolder> {
    CartItemCustomLayoutBinding binding;
    ArrayList<CartItem> items;
    Context context;

    public CartItemsRVAdapter( ArrayList<CartItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItemsViewHolder(CartItemCustomLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsViewHolder holder, int position) {
        binding.tvName.setText(items.get(position).getName());
        binding.tvDetails.setText(items.get(position).getDetails());
        binding.tvPrice.setText(String.valueOf(items.get(position).getPrice()));
        binding.tvQuantity.setText(String.valueOf(items.get(position).getQuantity()));
        binding.tvTotalPrice.setText(String.valueOf(items.get(position).getTotalPrice()));
        binding.btnRemoveProductFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.removeFromCart(items.get(position));
                Toast.makeText(context, "Product Removed Successfully..", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CartItemsViewHolder extends RecyclerView.ViewHolder{

        public CartItemsViewHolder(@NonNull CartItemCustomLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
