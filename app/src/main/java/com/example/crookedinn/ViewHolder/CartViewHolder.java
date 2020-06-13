package com.example.crookedinn.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crookedinn.Interface.ItemClickListener;
import com.example.crookedinn.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtItemName, txtItemPrice, txtItemQuantity, txtItemCategory;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtItemName = itemView.findViewById(R.id.layout_name);
        txtItemPrice = itemView.findViewById(R.id.layout_price);
        txtItemQuantity = itemView.findViewById(R.id.layout_quantity);
        txtItemCategory = itemView.findViewById(R.id.prodCategoryNull);


    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
